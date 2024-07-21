const express = require("express");
const eurekaServer = require("./node/src/config/eureka/eureka-server");
const axios = require('axios');
const app = express();
const db = require('./node/src/config/database/doctor');
const dotenv = require('dotenv');
const routes = require("./node/src/routes");
const port = 9005;

app.use(express.json());

  // const users = new mongoose.Schema({
  //   email: String,
  //   userName: String,
  //   fullName: String,
  //   sex: Boolean,
  //   age: String,
  // });  
  // const Users = mongoose.model('users', users);
  // const apiClient = axios.create({
  //   baseURL: 'http://users-healths:9003', // Thay thế bằng URL của dịch vụ khác
  //   headers: {'X-Custom-Header': 'foobar'}
  // });
  // app.get('/users/:userName', async (req, res) => {
  //   const userName = req.params.userName;
  //   // const rs = await Users.findOne({ userName: userName });
  //   // if (rs) {
  //   //   res.json(rs);
  //   // } else {
  //   //   res.status(404).send('Content not found');
  //   // }
  //   try {
  //       const response = await apiClient.get(`/users-health/${userName}`);
  //       console.log(response.data);
  //       return res.json(response.data);
  //     } catch (error) {
  //       console.error(error);
  //     }
  // })
  // app.get('/users/getOne/:userName', async (req, res) => {
  //   const userName = req.params.userName;
  //   const rs = await Users.findOne({ userName: userName });
  //   if (rs) {
  //     res.json(rs);
  //   } else {
  //     res.status(404).send('Content not found');
  //   }
    
  // })

eurekaServer.connectEureka();
dotenv.config()
routes(app);
db.connectDoctor();
  
app.listen(port, () => {
    console.log(`Content service running on port ${port}`);
});