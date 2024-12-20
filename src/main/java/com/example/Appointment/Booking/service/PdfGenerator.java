package com.example.Appointment.Booking.service;

import com.example.Appointment.Booking.model.AppointmentDetails;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfGenerator {

    public static byte[] generatePDF(AppointmentDetails appointmentDetails) throws DocumentException, IOException {
        String htmlContent = "<!DOCTYPE html>" +
                "<html xmlns='http://www.w3.org/1999/xhtml' lang='en'>" +
                "<head>" +
                "    <meta charset='UTF-8' />" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0' />" +
                "    <title>Appointment Scheduling Request</title>" +
                "    <style>" +
                "        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }" +
                "        .container { background-color: #ffffff; padding: 20px; margin: auto; max-width: 600px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); }" +
                "        h2 { color: #333333; }" +
                "        p { line-height: 1.6; color: #555555; }" +
                "        .appointment-details { margin: 20px 0; }" +
                "        .appointment-details p { margin: 5px 0; }" +
                "        .footer { margin-top: 30px; font-size: 12px; color: #888888; }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class='container'>" +
                "        <h2>Appointment Scheduling Request</h2>" +
                "        <p>Hello,</p>" +
                "        <p>I hope you’re doing well. Please schedule an appointment for the following patient:</p>" +
                "        <div class='appointment-details'>" +
                "            <p><strong>Patient Name:</strong> " + appointmentDetails.getPatientName() + "</p>" +
                "            <p><strong>Preferred Date:</strong> " + appointmentDetails.getDate() + "</p>" +
                "            <p><strong>Preferred Time:</strong> " + appointmentDetails.getTime() + "</p>" +
                "            <p><strong>Doctor/Provider:</strong> Dr. " + appointmentDetails.getDocName() + "</p>" +
                "            <p><strong>Department/Specialty:</strong> " + appointmentDetails.getSpecialist() + "</p>" +
                "            <p><strong>Location:</strong> " + appointmentDetails.getLocation() + "</p>" +
                "        </div>" +
                "        <p>Please confirm the appointment or provide alternative options if the preferred slot is unavailable. Let me know once the appointment is booked.</p>" +
                "        <p>Thank you for your assistance!</p>" +
                "        <p>Best Regards,<br />EasyMed<br /></p>" +
                "        <div><img src='cid:logoImage' alt='Logo' style='position: absolute; width: 100px; height: auto;' /></div>" +
                "        <div class='footer'>" +
                "            <p>This is a request for scheduling an appointment. Please ensure the details are correct before confirmation.</p>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";




        ByteArrayOutputStream  byteArrayOutputStream = new ByteArrayOutputStream();
//        ITextRenderer render = new ITextRenderer();
//        render.setDocumentFromString(htmlContent);
//        render.createPDF(byteArrayOutputStream);
//        render.finishPDF();
        Document document = new Document();
        PdfWriter pdfWriter =
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new ByteArrayInputStream(htmlContent.getBytes()));
        document.close();
        return byteArrayOutputStream.toByteArray();
    }



}
