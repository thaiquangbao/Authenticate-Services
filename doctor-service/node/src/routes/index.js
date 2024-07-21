
const doctorRoute = require('./doctor/doctorRoute.js');
function routes(app){
  app.use('/doctors', doctorRoute)
}
module.exports = routes;