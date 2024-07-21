const axios = require("axios");
class AuthClient {
  async deleteOneDoctorClients(id) {
    const apiClient = axios.create({
      baseURL: "http://localhost:9001", // Thay thế bằng URL của dịch vụ khác
      headers: { "X-Custom-Header": "foobar" },
    });
    await apiClient.delete(`/auth/deleteOneDoctor/${id}`);
  }
  async deleteAllDoctorClients(ids) {
    const apiClient = axios.create({
      baseURL: "http://localhost:9001", // Thay thế bằng URL của dịch vụ khác
      headers: { "X-Custom-Header": "foobar" },
    });
    await apiClient.post(`/auth/deleteManyDoctor`, ids);
  }
  async checkValidDoctorClients(id, checkValidData) {
    const apiClient = axios.create({
      baseURL: "http://localhost:9001", // Thay thế bằng URL của dịch vụ khác
      headers: { "X-Custom-Header": "foobar" },
    });
    const rs = await apiClient.post(
      `/auth/checkEmailAndPhone/${id}`,
      checkValidData
    );
    return rs;
  }
}
module.exports = new AuthClient();
