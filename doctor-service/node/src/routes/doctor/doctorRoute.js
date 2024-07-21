const express = require("express");
const useRouter = express.Router();
const doctorController = require("../../app/Controllers/DoctorController");
const upload = require("../../app/uploads/upload");
useRouter.put(
  "/take-password/:id",
  doctorController.takePassWord
);
useRouter.put(
  "/update-password/:id",
  doctorController.updatePassWord
);
useRouter.post(
  "/update-information",
  upload.single("image"),
  doctorController.updateInformation
);
useRouter.post(
  "/deleteManyDoctor",
  doctorController.deleteAllDoctor
);
useRouter.delete(
  "/deleteOne/:id",
  doctorController.deleteOneDoctor
);
useRouter.get("/getAll", doctorController.getAllDoctor);
useRouter.post("/update", doctorController.updateDoctor);
useRouter.get("/getById/:id", doctorController.getById);
useRouter.post("/save", doctorController.save);
useRouter.get("/", doctorController.index);
module.exports = useRouter;
