package com.example.Appointment.Booking.service;

import java.io.File;
import java.io.IOException;


import com.example.Appointment.Booking.model.*;
import com.example.Appointment.Booking.model.*;
import com.itextpdf.text.DocumentException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Service
public class EmailService {

    final Configuration configuration;
    final JavaMailSender javaMailSender;

    public EmailService(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(User user) throws MessagingException {//done
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("EasyMed Registration Success");
        helper.setTo(user.getEmail());
        String emailContent = EmailTemplate.getRegistrationSuccessTemplate(user);
        helper.setText(emailContent, true);
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
        helper.addInline("logoImage", res);
        javaMailSender.send(mimeMessage);
    }


    public void sendUserName(ForgotRequest forgotRequest, User user, String forgotUserName) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("EasyMed - UserName ");
        helper.setTo(forgotRequest.getEmail());
        String emailContent = EmailTemplate.getForgotUserNameTemplate(user);
        helper.setText(emailContent, true);
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
        helper.addInline("logoImage", res);
        javaMailSender.send(mimeMessage);
    }

    public void sendPassword(ForgotPasswordRequest forgotRequest) throws MessagingException, IOException, TemplateException {
        //todo add reset link in mail
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("EasyMed - Reset Password");
        helper.setTo(forgotRequest.getEmail());
        String content = EmailTemplate.getForgotPassTemplate(forgotRequest.getToken());
        helper.setText(content, true);
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
        helper.addInline("logoImage", res);
        javaMailSender.send(mimeMessage);
    }

    public void sentAppointmentDtlsToPatient(AppointmentDetails appointmentDetails, User user) throws MessagingException, IOException, TemplateException, DocumentException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("Your upcoming Appointments.");
        helper.setTo(user.getEmail());
        String emailContent = EmailTemplate.getAppoinmentConfmTemplate(appointmentDetails);
        byte[] pdfContent = PdfGenerator.generatePDF(appointmentDetails);
        InputStreamSource attachment = new ByteArrayResource(pdfContent);
        helper.addAttachment(appointmentDetails.getBookingId()+".pdf", attachment);
        helper.setText(emailContent, true);
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
        helper.addInline("logoImage", res);
        javaMailSender.send(mimeMessage);
    }

    public void sentAppointmentDtlsToHospital(AppointmentDetails appointmentDetails) throws MessagingException, IOException, TemplateException, DocumentException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("Your upcoming Appointments.");
        helper.setTo(appointmentDetails.getSchedulerEmail());
        String emailContent = EmailTemplate.getAppoinmentConfmDoctorTemplate(appointmentDetails);
        byte[] pdfContent = PdfGenerator.generatePDF(appointmentDetails);
        InputStreamSource attachment = new ByteArrayResource(pdfContent);
        helper.addAttachment(appointmentDetails.getBookingId()+".pdf", attachment);
        helper.setText(emailContent, true);
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
        helper.addInline("logoImage", res);
        javaMailSender.send(mimeMessage);
    }

    public void sendOTPEmail(User user, String otp) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("EasyMed login OTP.");
        helper.setTo(user.getEmail());
        String emailContent = EmailTemplate.getLoginSuccess(user,otp);
        helper.setText(emailContent, true);
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
        helper.addInline("logoImage", res);
        javaMailSender.send(mimeMessage);
    }

    public void sendOTPEmailForScheduler(SchedulerOTP hospitalMail, String otp, String hospitalEmail) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("EasyMed login OTP.");
        helper.setTo(hospitalEmail);
        String emailContent = EmailTemplate.sendOtp(hospitalMail,otp);
        helper.setText(emailContent, true);
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
        helper.addInline("logoImage", res);
        javaMailSender.send(mimeMessage);
    }

    public void sendCancelledMailToPatient(SchedulerHospitalTiming schedulerHospitalTiming1, User user) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("Appointment Cancelled.");
        helper.setTo(user.getEmail());
        String emailContent = EmailTemplate.getCancelAppointmentDtl(schedulerHospitalTiming1);
        helper.setText(emailContent, true);
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
        helper.addInline("logoImage", res);
        javaMailSender.send(mimeMessage);
    }

    public void sendCancelledMailToHospital(SchedulerHospitalTiming schedulerHospitalTiming, String email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("Appointment Cancellation for " + schedulerHospitalTiming.getBookingId());
        helper.setTo(email);
        String emailContent = EmailTemplate.getCancelAppointmentDtlToHospital(schedulerHospitalTiming);
        helper.setText(emailContent, true);
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
        helper.addInline("logoImage", res);
        javaMailSender.send(mimeMessage);
    }

    public void sentRescheduleAppointmentDtlsToPatient(AppointmentDetails appointmentDetails, User user) throws MessagingException, IOException, TemplateException, DocumentException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("Appointment has been Rescheduled.");
        helper.setTo(user.getEmail());
        String emailContent = EmailTemplate.getRescheduleAppoinmentConfmTemplate(appointmentDetails);
        byte[] pdfContent = PdfGenerator.generatePDF(appointmentDetails);
        InputStreamSource attachment = new ByteArrayResource(pdfContent);
        helper.addAttachment(appointmentDetails.getBookingId()+".pdf", attachment);
        helper.setText(emailContent, true);
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
        helper.addInline("logoImage", res);
        javaMailSender.send(mimeMessage);
    }

    public void sentRescheduleAppointmentDtlsToHospital(AppointmentDetails appointmentDetails) throws MessagingException, IOException, TemplateException, DocumentException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("Appointment has been Rescheduled.");
        helper.setTo(appointmentDetails.getSchedulerEmail());
        String emailContent = EmailTemplate.getRescheduleAppoinmentConfmDoctorTemplate(appointmentDetails);
        byte[] pdfContent = PdfGenerator.generatePDF(appointmentDetails);
        InputStreamSource attachment = new ByteArrayResource(pdfContent);
        helper.addAttachment(appointmentDetails.getBookingId()+".pdf", attachment);
        helper.setText(emailContent, true);
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
        helper.addInline("logoImage", res);
        javaMailSender.send(mimeMessage);
    }
}