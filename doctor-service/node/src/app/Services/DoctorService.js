const doctors = require("../models/doctorModel");
const axios = require("axios");
const authClient = require("../Clients/AuthClient");
const passWordService = require("../../untill/PassWord");
const uploadToS3 = require("../../config/AWS/S3");
class DoctorService {
  async save(doctorData) {
    try {
      const checkData = await doctors.findOne({
        phone: doctorData.phone,
      });
      if (checkData) {
        return 0;
      }
      // Tạo một instance mới của Doctor với dữ liệu nhận được
      const doctor = new doctors(doctorData);
      doctor.id_user = doctorData.id;
      // Lưu instance vào cơ sở dữ liệu
      await doctor.save();

      // Trả về dữ liệu doctor sau khi lưu thành công
      return doctor;
    } catch (error) {
      // Xử lý lỗi nếu có
      throw error;
    }
  }
  async getOne(id) {
    try {
      const rs = await doctors.findOne({ id_user: id });
      return rs;
    } catch (error) {
      throw error;
    }
  }
  async updateClients(id, data) {
    const apiClient = axios.create({
      baseURL: "http://localhost:9001", // Thay thế bằng URL của dịch vụ khác
      headers: { "X-Custom-Header": "foobar" },
    });
    await apiClient.put(`/auth/updateDoctor/${id}`, data);
  }
  async updateOne(doctorData) {
    try {
      const checkData = await doctors.findOne({
        id_user: doctorData.id,
      });
      if (!checkData) {
        return 0;
      }
      const existingDoctor = await doctors.findOne({
        $or: [
          {
            phone: doctorData.phone,
            id_user: { $ne: doctorData.id },
          },
          {
            email: doctorData.email,
            id_user: { $ne: doctorData.id },
          },
        ],
      });
      const checkAuth =
        await authClient.checkValidDoctorClients(
          doctorData.id,
          {
            phone: doctorData.phone,
            email: doctorData.email,
          }
        );
      if (
        (existingDoctor &&
          existingDoctor.phone === doctorData.phone) ||
        checkAuth.data === 2
      ) {
        return 2; // Trùng số điện thoại
      }
      if (
        (existingDoctor &&
          existingDoctor.email === doctorData.email) ||
        checkAuth.data === 3
      ) {
        return 3; // Trùng email
      }
      // Lưu instance vào cơ sở dữ liệu
      const rs = await doctors.findOneAndUpdate(
        {
          id_user: checkData.id_user,
        },
        doctorData,
        { new: true }
      );
      this.updateClients(doctorData.id, rs);
      // Trả về dữ liệu doctor sau khi lưu thành công
      return rs;
    } catch (error) {
      // Xử lý lỗi nếu có
      console.log(error);
      throw error;
    }
  }
  async getAll() {
    try {
      const rs = await doctors.find();
      return rs;
    } catch (error) {
      throw error;
    }
  }

  async deleteOne(id) {
    try {
      const checkData = await doctors.findOne({
        id_user: id,
      });
      if (!checkData) {
        return 0;
      }
      await doctors.findOneAndDelete({
        id_user: id,
      });
      await authClient.deleteOneDoctorClients(id);
      return 1;
    } catch (error) {
      throw error;
    }
  }
  async deleteAll(idses) {
    try {
      const ids = idses.ids;
      await doctors.deleteMany({
        id_user: { $in: ids },
      });
      await authClient.deleteAllDoctorClients(ids);
      return 1;
    } catch (error) {
      throw error;
    }
  }
  async updateInformation(doctor, image, isWeb) {
    const doctorFound = await doctors.findOne({
      id_user: Number(doctor.id),
    });
    if (!doctorFound) {
      return 0;
    }
    const existingDoctor = await doctors.findOne({
      $or: [
        {
          phone: doctor.phone,
          id_user: { $ne: Number(doctor.id) },
        },
        {
          email: doctor.email,
          id_user: { $ne: Number(doctor.id) },
        },
      ],
    });

    if (existingDoctor) {
      if (existingDoctor.phone === doctor.phone) {
        return 2; // Trùng số điện thoại
      }
      if (existingDoctor.email === doctor.email) {
        return 3; // Trùng email
      }
    }
    if (image) {
      if (isWeb === true) {
        const url = await uploadToS3(
          `image_${Date.now().toString()}_${
            image.originalname.split(".")[0]
          }`,
          image.buffer,
          image.mimetype
        );
        doctor.image = url.url;
      } else {
        const fileData = await Buffer.from(
          image.base64,
          "base64"
        );
        const url = await uploadToS3(
          `image_${Date.now().toString()}_${
            image.fileName?.split(".")[0]
          }`,
          fileData,
          (doctor.image = url.url)
        );
        doctor.image = url.url;
      }
    }
    const rs = await doctors.findOneAndUpdate(
      {
        id_user: doctorFound.id_user,
      },
      doctor,
      { new: true }
    );
    this.updateClients(Number(doctor.id), rs);
    return rs;
  }
  async updatePassWord(id, passWord, newPassword) {
    const doctorExist = await doctors.findOne({
      id_user: id,
    });
    if (!doctorExist) {
      return 0;
    }
    const isPasswordMatch =
      await passWordService.comparePasswords(
        passWord,
        doctorExist.passWord
      );
    if (isPasswordMatch == true) {
      const hashedNewPassword =
        await passWordService.hashPassword(newPassword);
      const updatedDoctor = await doctors.findOneAndUpdate(
        {
          id_user: doctorExist.id_user,
        },
        { passWord: hashedNewPassword },
        { new: true }
      );
      this.updateClients(id, updatedDoctor);
      return updatedDoctor;
    } else {
      return 2;
    }
  }
  async takePassWord(id, newPassWord) {
    const doctorExist = await doctors.findOne({
      id_user: id,
    });
    if (!doctorExist) {
      return 0;
    }
    const hashedNewPassword =
      await passWordService.hashPassword(newPassWord);
    const rs = await doctors.findOneAndUpdate(
      {
        id_user: doctorExist.id_user,
      },
      { passWord: hashedNewPassword },
      { new: true }
    );
    this.updateClients(id, rs);
    return rs;
  }
}
module.exports = new DoctorService();
