const mongoose = require('mongoose');
async function connectDoctor() {  
  try {
    mongoose.connect(process.env.MONGO_DB);
    console.log('Connect Doctor-Service thành công !!!');
  } catch (error) {
    console.log('Connect không thành công');
  }
}
module.exports = { connectDoctor };
