const doctorService = require("../Services/DoctorService");
const decode = require("../../untill/decodeToken");

class DoctorController {
  index(req, res, next) {
    res.send("respond with a resource");
  }
  async save(req, res) {
    try {
      const doctorData = req.body;
      const rs = await doctorService.save(doctorData);
      if (rs === 0) {
        return res
          .status(500)
          .json(
            "Số điện thoại của bác sĩ này đã tốn tại!!!"
          );
      }
      return res.status(200).json(rs);
    } catch (error) {
      return res.status(500).json(error);
    }
  }
  async getById(req, res) {
    try {
      const { id } = req.params;
      const rs = await doctorService.getOne(id);
      if (!rs) {
        return res
          .status(404)
          .json("Không tìm thấy bác sĩ này!!!");
      }
      return res.status(200).json(rs);
    } catch (error) {
      return res.status(500).json(error);
    }
  }
  async updateDoctor(req, res) {
    try {
      const rs = await doctorService.updateOne(req.body);
      const accessToken = req.headers["accesstoken"];
      const refreshToken = req.headers["refreshtoken"];
      const token = { accessToken, refreshToken };
      if (rs === 0) {
        return res.status(500).json({
          data: "Bác sỉ này không tồn tại!!!",
          token,
        });
      }
      if (rs === 2) {
        return res.status(500).json({
          data: "Số điện thoại này đã có bác sĩ sử dụng!!!",
          token,
        });
      }
      if (rs === 3) {
        return res.status(500).json({
          data: "Địa chỉ email này đã có bác sĩ sử dụng!!!",
          token,
        });
      }
      return res.status(200).json({ data: rs, token });
    } catch (error) {
      return res.status(500).json(error);
    }
  }
  async getAllDoctor(req, res) {
    try {
      const accessToken = req.headers["accesstoken"];
      const refreshToken = req.headers["refreshtoken"];
      const token = { accessToken, refreshToken };
      const rs = await doctorService.getAll();
      return res.status(200).json({ data: rs, token });
    } catch (error) {
      return res.status(500).json(error);
    }
  }
  async deleteOneDoctor(req, res) {
    try {
      const { id } = req.params;
      const accessToken = req.headers["accesstoken"];
      const refreshToken = req.headers["refreshtoken"];
      const token = { accessToken, refreshToken };
      const rs = await doctorService.deleteOne(id);
      if (rs === 0) {
        return res.status(500).json({
          data: "Bác sĩ này không tồn tại!!!",
          token,
        });
      }
      return res
        .status(200)
        .json({ data: "Xóa thành công", token });
    } catch (error) {
      return res.status(500).json(error);
    }
  }
  async deleteAllDoctor(req, res) {
    try {
      const ids = req.body;
      const accessToken = req.headers["accesstoken"];
      const refreshToken = req.headers["refreshtoken"];
      const token = { accessToken, refreshToken };
      await doctorService.deleteAll(ids);
      return res
        .status(200)
        .json({ data: "Xóa thành công", token });
    } catch (error) {
      return res.status(500).json(error);
    }
  }
  async updateInformation(req, res) {
    try {
      const doctor = req.body;
      console.log(doctor);
      let image = req.file;
      console.log(image);
      // if (req.body.image) {
      //   image = JSON.parse(req.body.image);
      // }
      const rs = await doctorService.updateInformation(
        doctor,
        image,
        true
      );
      const accessToken = req.headers["accesstoken"];
      const refreshToken = req.headers["refreshtoken"];
      const token = { accessToken, refreshToken };
      if (rs === 0) {
        return res.status(500).json({
          data: "Bác sĩ này không tồn tại!!!",
          token,
        });
      }
      if (rs === 2) {
        return res.status(500).json({
          data: "Số điện thoại này đã có bác sĩ sử dụng!!!",
          token,
        });
      }
      if (rs === 3) {
        return res.status(500).json({
          data: "Địa chỉ email này đã có bác sĩ sử dụng!!!",
          token,
        });
      }

      return res.status(200).json({ data: rs, token });
    } catch (error) {
      console.log(error);
      return res.status(500).json(error);
    }
  }
  async updatePassWord(req, res) {
    try {
      const id = req.params.id;
      const { passWord, newPassword } = req.body;
      const rs = await doctorService.updatePassWord(
        id,
        passWord,
        newPassword
      );
      const accessToken = req.headers["accesstoken"];
      const refreshToken = req.headers["refreshtoken"];
      const token = { accessToken, refreshToken };
      if (rs === 0) {
        return res.status(500).json({
          data: "Bác sĩ này không tồn tại!!!",
          token,
        });
      }
      if (rs === 2) {
        return res.status(500).json({
          data: "Mật khẩu cũ không đúng!!!",
          token,
        });
      }
      return res.status(200).json({ data: rs, token });
    } catch (error) {
      return res.status(500).json(error);
    }
  }
  async takePassWord(req, res) {
    try {
      const id = req.params.id;
      const accessToken = req.headers["accesstoken"];
      const refreshToken = req.headers["refreshtoken"];
      const token = { accessToken, refreshToken };
      const { newPassword } = req.body;
      const rs = await doctorService.takePassWord(
        id,
        newPassword
      );
      if (rs === 0) {
        return res.status(500).json({
          data: "Bác sĩ không tồn tại!!!",
          token,
        });
      }
      return res.status(200).json({ data: rs, token });
    } catch (error) {
      return res.status(500).json(error);
    }
  }
}
module.exports = new DoctorController();
