const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const Rating = require('../models/doctorModel');

const doctors = new Schema({
  fullName: String,
  address: String,
  passWord: String,
  email: String,
  sex: Boolean,
  role: String,
  phone: String,
  image: String,
  dateOfBirth: String,
  processSignup: String,
  specialize: String,
  id_user: Number,
  rating: {
    type: [
      {
        rate: Number,
        user_rating: {
          id: Number,
          name: String,
          image: String,
        }
      }
    ],
    default: [] 
  },

}, {timestamps : true})
module.exports = mongoose.model('doctors',doctors)