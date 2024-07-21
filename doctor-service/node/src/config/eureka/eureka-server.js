const Eureka = require("eureka-js-client").Eureka;
async function connectEureka() {  
  const client = new Eureka({
    instance: {
      app: 'doctor-service',
      hostName: process.env.EUREKA_INSTANCE_HOSTNAME || 'localhost',
      ipAddr: process.env.EUREKA_INSTANCE_IP_ADDRESS || '127.0.0.1',
      port: {
        '$': 9005,
        '@enabled': 'true',
    },
      vipAddress: 'doctor-service',
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

}
module.exports = { connectEureka };