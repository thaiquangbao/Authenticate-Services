const jwt = require("jsonwebtoken");
const Buffer = require("buffer").Buffer;
class DecodeToken {
  async decodeToken(token) {
    // Sử dụng hàm
    const secretString =
      "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
    // Giải mã Base64 thành chuỗi bí mật
    // const secretKey = Buffer.from(
    //   secretString,
    //   "base64"
    // ).toString("utf8");
    console.log(token);
    try {
      const decoded = jwt.verify(token, secretString, {
        algorithms: ["HS256"],
      });
      console.log("Token decoded:", decoded);
      return { success: true, decoded };
    } catch (error) {
      console.error("Error decoding token:", error.message);
      return { success: false, message: error.message };
    }
  }
}

module.exports = new DecodeToken();
