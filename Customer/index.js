const express = require("express");
const mongoose = require('mongoose');
const Eureka = require("eureka-js-client").Eureka;
const axios = require('axios');
const app = express();
const port = 3000;

app.use(express.json());
try {
    mongoose.connect("mongodb+srv://thaibao:123@cluster0.4toawde.mongodb.net/Health_Service?retryWrites=true&w=majority");
    console.log('Connect thành công !!!');
  } catch (error) {
    console.log('Connect không thành công');
  }
  const users = new mongoose.Schema({
    email: String,
    userName: String,
    fullName: String,
    sex: Boolean,
    age: String,
  });  
  const Users = mongoose.model('users', users);
  const apiClient = axios.create({
    baseURL: 'http://users-healths:9003', // Thay thế bằng URL của dịch vụ khác
    headers: {'X-Custom-Header': 'foobar'}
  });
  app.get('/users/:userName', async (req, res) => {
    const userName = req.params.userName;
    // const rs = await Users.findOne({ userName: userName });
    // if (rs) {
    //   res.json(rs);
    // } else {
    //   res.status(404).send('Content not found');
    // }
    try {
        const response = await apiClient.get(`/users-health/${userName}`);
        console.log(response.data);
        return res.json(response.data);
      } catch (error) {
        console.error(error);
      }
  })
  app.get('/users/getOne/:userName', async (req, res) => {
    const userName = req.params.userName;
    const rs = await Users.findOne({ userName: userName });
    if (rs) {
      res.json(rs);
    } else {
      res.status(404).send('Content not found');
    }
    
  })
    const client = new Eureka({
        instance: {
          app: 'users-service-health',
          hostName: process.env.EUREKA_INSTANCE_HOSTNAME || 'localhost',
          ipAddr: process.env.EUREKA_INSTANCE_IP_ADDRESS || '127.0.0.1',
          port: {
            '$': 3000,
            '@enabled': 'true',
        },
          vipAddress: 'users-service-health',
          dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
          },
        },
        eureka: {
          host: process.env.EUREKA_SERVER_HOST || 'localhost',
          port: process.env.EUREKA_SERVER_PORT || 8761,
          servicePath: '/eureka/apps/'
        }
      });
      
      client.start(error => {
        console.log('Eureka client started with error:', error);
    });
  
  
app.listen(port, () => {
    console.log(`Content service running on port ${port}`);
});