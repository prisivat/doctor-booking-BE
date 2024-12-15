package com.example.Appointment.Booking.service;

import com.example.Appointment.Booking.model.AppointmentDetails;
import com.example.Appointment.Booking.model.SchedulerHospitalTiming;
import com.example.Appointment.Booking.model.SchedulerOTP;
import com.example.Appointment.Booking.model.User;
import com.example.Appointment.Booking.model.*;

public class EmailTemplate {

    public static String getForgotPassTemplate(String accessToken){
        return "<html>" +
                "     <head>             <meta charset='UTF-8'>      " +
                "                            <meta name='viewport' content='width=device-width, initial-scale=1.0'>     " +
                "                                               <style>                     " +
                "                     body {                             font - family: Arial, sans-serif;                     " +
                "                     background-color: #f4f4f4;                         margin: 0;                         padding: 20px;    " +
                "                 " +
                "                      }                         .container {                             background - color: #ffffff;      " +
                "                                    padding: 20px;                         margin: auto;                       " +
                "                   max-width: 600px;                         border-radius: 8px;                       " +
                "                   box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);         }                         h2 {                             color: #333333;         }                         p {                             line - height: 1.6;                         color: #555555;         }                         .appointment-details {                             margin: 20px 0;         }                         .appointment-details p {                             margin: 5px 0;         }     " +
                "                                     .footer {                             margin - top: 30px;                         font-size: 12px;          " +
                "                                color: #888888;         }                     </style>                 </head> " +
                "<body style='margin: 0; padding: 0; font-family: Arial, sans-serif; '>   " +
                "                 <div style='position: relative; padding: 20px;'>   " +
                "                     " +
                "                 <div style='margin-top: 10px; color:black !important'>  " +
                "                                 Hello,<br/><br/>  " +
                "                                 We’ve received a request to reset your EasyMed password.<br/><br/></div>  " +
                "                               <div> <span style = 'color: black'> Access Token: <b>"+accessToken+"</b></span></br></br></br></div> " +
                "                                 <div style = 'color: black'> 'Note: Token valid for 10 minutes. ' <br/><br/>Your password should have 8 or more characters with at least one uppercase letter, lowercase letter, number and special character. <br/><br/>  " +
                " <a href='http://localhost:3000/resetPassword'>Reset Password</a><br/><br/>" +
                "                                 If you have any queries, please contact the help desk. </div>  <br/><br/>  " +
                "                                <div style = 'color : black'> Kind regards,</br></br></div>  " +
                "                                 <div style = 'color : black'>EasyMed</br></br></br></div>  " +
                "                                 </div></br><div><img src='cid:logoImage' alt='Logo' style='position: absolute;  width: 100px; height: auto;' />  " +
                "                                 </div></div></body></html>";
    }

    public static String getForgotUserNameTemplate(User user){
        return "<html>" +
                "     <head>             <meta charset='UTF-8'>      " +
                "                            <meta name='viewport' content='width=device-width, initial-scale=1.0'>     " +
                "                                                  <style>                     " +
                "                     body {                             font - family: Arial, sans-serif;                     " +
                "                     background-color: #f4f4f4;                         margin: 0;                         padding: 20px;    " +
                "                 " +
                "                      }                         .container {                             background - color: #ffffff;      " +
                "                                    padding: 20px;                         margin: auto;                       " +
                "                   max-width: 600px;                         border-radius: 8px;                       " +
                "                   box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);         }                         h2 {                             color: #333333;         }                         p {                             line - height: 1.6;                         color: #555555;         }                         .appointment-details {                             margin: 20px 0;         }                         .appointment-details p {                             margin: 5px 0;         }     " +
                "                                     .footer {                             margin - top: 30px;                         font-size: 12px;          " +
                "                                color: #888888;         }                     </style>                 </head> <body style='margin: 0; padding: 0; font-family: Arial, sans-serif; '>           " +
                "        <div style='position: relative; padding: 20px;'>       " +
                "<div style='margin-top: 10px; color:black !important'>             " +
                "      Hello <b>"+user.getFirstName()+"</b>,<br/><br/>                " +
                "   Please use this User Name:<b>"+ user.getUserName() +"</b> and try to login EasyMed with your credentials.<br/><br/></div>     " +
                "   <div style = 'color: black'>              " +
                "     If you have any queries, please contact the help desk. </div> " +
                " <br/><br/>  <div style = 'color : black'> Kind regards,</br></br ></div >       " +
                "" +
                "<div style='color : black'>EasyMed</br></br ></br ></div >  </div ></br > " +
                "" +
                "<div><img src='cid:logoImage' alt='Logo' style='position: absolute;  width: 100px; height: auto;' />" +
                "  </div></div ></body ></html >";
    }

    public static String getRegistrationSuccessTemplate(User user){
        return "<html>" +
                "     <head>             <meta charset='UTF-8'>      " +
                "                            <meta name='viewport' content='width=device-width, initial-scale=1.0'>     " +
                "                                                   <style>                     " +
                "                     body {                             font - family: Arial, sans-serif;                     " +
                "                     background-color: #f4f4f4;                         margin: 0;                         padding: 20px;    " +
                "                 " +
                "                      }                         .container {                             background - color: #ffffff;      " +
                "                                    padding: 20px;                         margin: auto;                       " +
                "                   max-width: 600px;                         border-radius: 8px;                       " +
                "                   box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);         }                         h2 {                             color: #333333;         }                         p {                             line - height: 1.6;                         color: #555555;         }                         .appointment-details {                             margin: 20px 0;         }                         .appointment-details p {                             margin: 5px 0;         }     " +
                "                                     .footer {                             margin - top: 30px;                         font-size: 12px;          " +
                "                                color: #888888;         }                     </style>                 </head> " +
                "<body style='margin: 0; padding: 0; font-family: Arial, sans-serif; '>           " +
                "        <div style='position: relative; padding: 20px;'>       " +
                "<div style='margin-top: 10px; color:black !important'>             " +
                "      Dear <b>"+user.getFirstName()+"</b>,<br/><br/>                " +
                "   Please use this User Name:<b>"+ user.getUserName() +"</b> to login EasyMed.<br/><br/></div>     " +
                "   <div style = 'color: black'>              " +
                "     If you have any queries, please contact the help desk. </div> " +
                " <br/><br/>  <div style = 'color : black'> Kind regards,</br></br ></div >       " +
                
                "<div style='color : black'>EasyMed</br></br ></br ></div >  </div ></br > " +
                
                "<div><img src='cid:logoImage' alt='Logo' style='position: absolute;  width: 100px; height: auto;' />" +
                "  </div></div ></body ></html >";
    }

    public static String getAppoinmentConfmTemplate(AppointmentDetails appointmentDetails){
        return "   <html lang='en'>    " +
                "     <head>             <meta charset='UTF-8'>     " +
                "            <meta name='viewport' content='width=device-width, initial-scale=1.0'>    " +
                "                 <title>Appointment Confirmation</title>                     <style>                    " +
                "     body {                             font - family: Arial, sans-serif;                    " +
                "     background-color: #f4f4f4;                         margin: 0;                         padding: 20px;   " +
                "" +
                "      }                         .container {                             background - color: #ffffff;     " +
                "                    padding: 20px;                         margin: auto;                      " +
                "   max-width: 600px;                         border-radius: 8px;                      " +
                "   box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);         }                         h2 {                             color: #333333;         }                         p {                             line - height: 1.6;                         color: #555555;         }                         .appointment-details {                             margin: 20px 0;         }                         .appointment-details p {                             margin: 5px 0;         }    " +
                "                     .footer {                             margin - top: 30px;                         font-size: 12px;         " +
                "                color: #888888;         }                     </style>                 </head>                 <body> " +
                "                    <div class='container'>                         <h2>Your EasyMed Appointment Confirmation – "+appointmentDetails.getDate() +" " + appointmentDetails.getTime()+"</h2>                          <p>Dear "+appointmentDetails.getPatientName()+",</p>                         " +
                " <p>Thank you for booking your appointment with "+ appointmentDetails.getHospitalName()+". We are pleased to confirm your appointment as follows:</p>   " +
                "                       <div class='appointment-details'>                             <p><strong>Booking ID:</strong> "+appointmentDetails.getBookingId()+"</p>   <p><strong>Date:</strong> "+appointmentDetails.getDate()+"</p>  " +
                "                           <p><strong>Time:</strong> "+appointmentDetails.getTime()+"</p>                         " +
                "    <p><strong>Doctor/Provider:</strong> Dr. "+appointmentDetails.getDocName()+"</p>                           " +
                "  <p><strong>Department/Specialty:</strong> "+appointmentDetails.getSpecialist()+"</p>                           " +
                "  <p><strong>Location:</strong> "+appointmentDetails.getLocation()+"</p>                         </div>       " +
                "                   <p>Please arrive 15 minutes prior to your scheduled appointment time to complete any necessary paperwork. Remember to bring your insurance card and a valid photo ID.</p>         " +
                "                 <p>If you have any questions or need to reschedule, please contact us at 044 - 24356282 or email EasyMed@gmail.com.</p>  " +
                "                        <p>We look forward to seeing you soon!</p>                        " +
                "   <div style='color : black'> Kind regards,</br></br ></div >                 <div style='color : black'>EasyMed</br></br ></br ></div >" +
                " </br > <div><img src='cid:logoImage' alt='Logo' style='position: absolute;  width: 100px; height: auto;' /></div>     " +
                "<div class='footer'>         <p>This is an automated message, please do not reply to this email.</p>     </div> </div> </body ></html >";
    }

    public static String getAppoinmentConfmDoctorTemplate(AppointmentDetails appointmentDetails){
        return "   <!DOCTYPE html>  <html lang=  'en  '>  " +
                "     <head>             <meta charset='UTF-8'/>      " +
                "                            <meta name='viewport' content='width=device-width, initial-scale=1.0'>     " +
                "                                 <title>Appointment Scheduling Request</title>                     <style>                     " +
                "                     body {                             font - family: Arial, sans-serif;                     " +
                "                     background-color: #f4f4f4;                         margin: 0;                         padding: 20px;    " +
                "                 " +
                "                      }                         .container {                             background - color: #ffffff;      " +
                "                                    padding: 20px;                         margin: auto;                       " +
                "                   max-width: 600px;                         border-radius: 8px;                       " +
                "                   box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);         }                         h2 {                             color: #333333;         }                         p {                             line - height: 1.6;                         color: #555555;         }                         .appointment-details {                             margin: 20px 0;         }                         .appointment-details p {                             margin: 5px 0;         }     " +
                "                                     .footer {                             margin - top: 30px;                         font-size: 12px;          " +
                "                                color: #888888;         }                     </style>                 </head>   <body>     " +
                " <div class=  'container  '>          <h2>Appointment Scheduling Request</h2>            <p>Hello,</p>        " +
                "    <p>I hope you’re doing well. Please schedule an appointment for the following patient:</p>           " +
                " <div class=  'appointment-details  '>              <p><strong>Patient Name:</strong> "+appointmentDetails.getPatientName()+"</p>             " +
                " <p><strong>Preferred Date:</strong> "+appointmentDetails.getDate()+"</p>              <p><strong>Preferred Time:</strong> "+appointmentDetails.getTime()+"</p>        " +
                "      <p><strong>Doctor/Provider:</strong> Dr. "+appointmentDetails.getDocName()+"</p>              <p><strong>Department/Specialty:</strong>" +
                " "+appointmentDetails.getSpecialist()+"</p>              <p><strong>Location:</strong> "+appointmentDetails.getLocation()+"</p>          </div>         " +
                "   <p>Please confirm the appointment or provide alternative options if the preferred slot is unavailable. Let me know once the appointment is booked.</p>        " +
                "          <p>Thank you for your assistance!</p>            <p>Best Regards,<br>EasyMed<br></p>          " +
                "  <div><img src='cid:logoImage' alt='Logo' style='position: absolute;  width: 100px; height: auto;' /></div>        " +
                "    <div class=  'footer  '>              <p>This is a request for scheduling an appointment. " +
                "Please ensure the details are correct before confirmation.</p>          </div>      </div>  </body>  </html>  ";
    }


    public static String getLoginSuccess(User user, String otp) {

        return "<html><body style='margin: 0; padding: 0; font-family: Arial, sans-serif; '>   " +
                "                 <div style='position: relative; padding: 20px;'>   " +
                "                     " +
                "                 <div style='margin-top: 10px; color:black !important'>  " +
                "                                 Hello "+user.getFirstName()+",<br/><br/>  " +
                "                                 <br/><br/></div>  " +
                "                               <div> <span style = 'color: black'> Use this OTP : <b>"+otp+" </b> to login </span></br></br></br></div> " +
                "                                 <div style = 'color: black'> 'Note: OTP valid for 2 minutes. ' <br/><br/> <br/><br/>  " +
                "                                 If you have any queries, please contact the help desk. </div>  <br/><br/>  " +
                "                                <div style = 'color : black'> Kind regards,</br></br></div>  " +
                "                                 <div style = 'color : black'>EasyMed</br></br></br></div>  " +
                "                                 </div></br><div><img src='cid:logoImage' alt='Logo' style='position: absolute;  width: 100px; height: auto;' />  " +
                "                                 </div></div></body></html>";
    }

    public static String sendOtp(SchedulerOTP schedulerMailOtpDetails, String otp) {

        return "<html><body style='margin: 0; padding: 0; font-family: Arial, sans-serif; '>   " +
                "                 <div style='position: relative; padding: 20px;'>   " +
                "                     " +
                "                 <div style='margin-top: 10px; color:black !important'>  " +
                "                                 Hello "+schedulerMailOtpDetails.getHospitalName()+",<br/><br/>  " +
                "                                 <br/><br/></div>  " +
                "                               <div> <span style = 'color: black'> Use this OTP : <b>"+otp+" </b> to Register/Login </span></br></br></br></div> " +
                "                                 <div style = 'color: black'> 'Note: OTP valid for 2 minutes. ' <br/><br/> <br/><br/>  " +
                "                                 If you have any queries, please contact the help desk. </div>  <br/><br/>  " +
                "                                <div style = 'color : black'> Kind regards,</br></br></div>  " +
                "                                 <div style = 'color : black'>EasyMed</br></br></br></div>  " +
                "                                 </div></br><div><img src='cid:logoImage' alt='Logo' style='position: absolute;  width: 100px; height: auto;' />  " +
                "                                 </div></div></body></html>";
    }

    public static String getCancelAppointmentDtl(SchedulerHospitalTiming schedulerHospitalTiming) {

        return "   <html lang='en'>    " +
                "     <head>             <meta charset='UTF-8'>     " +
                "            <meta name='viewport' content='width=device-width, initial-scale=1.0'>    " +
                "                 <title>Appointment Cancelled</title>                     <style>                    " +
                "     body {                             font - family: Arial, sans-serif;                    " +
                "     background-color: #f4f4f4;                         margin: 0;                         padding: 20px;   " +
                "" +
                "      }                         .container {                             background - color: #ffffff;     " +
                "                    padding: 20px;                         margin: auto;                      " +
                "   max-width: 600px;                         border-radius: 8px;                      " +
                "   box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);         }                         h2 {                             color: #333333;         }                         p {                             line - height: 1.6;                         color: #555555;         }                         .appointment-details {                             margin: 20px 0;         }                         .appointment-details p {                             margin: 5px 0;         }    " +
                "                     .footer {                             margin - top: 30px;                         font-size: 12px;         " +
                "                color: #888888;         }                     </style>                 </head>                 <body> " +
                "                    <div class='container'>                         <h2>Your EasyMed Appointment Cancelled  – "+schedulerHospitalTiming.getDate() +" " + schedulerHospitalTiming.getTime()+"</h2>                          <p>Dear "+schedulerHospitalTiming.getPatientName()+",</p>                         " +
                " <p>Your Booking for "+ schedulerHospitalTiming.getHospitalName()+" is Cancelled Successfully. We are pleased to confirm your cancelled appointment as follows:</p>   " +
                "                       <div class='appointment-details'>                             <p><strong>Booking ID:</strong> "+schedulerHospitalTiming.getBookingId()+"</p>   <p><strong>Date:</strong> "+schedulerHospitalTiming.getDate()+"</p>  " +
                "                           <p><strong>Time:</strong> "+schedulerHospitalTiming.getTime()+"</p>                         " +
                "    <p><strong>Doctor/Provider:</strong> Dr. "+schedulerHospitalTiming.getDocName()+"</p>                           " +
                "  <p><strong>Department/Specialty:</strong> "+schedulerHospitalTiming.getSpecialist()+"</p>                           " +
                "  <p><strong>Location:</strong> "+schedulerHospitalTiming.getLocation()+"</p>                         </div>       " +
                "                   <p>Feel free to book again, using EasyMed.</p>         " +
                "                 <p>If you have any questions or need to reschedule, please contact us at 044 - 24356282 or email EasyMed@gmail.com.</p>  " +
                "                        <p>We look forward to seeing you soon!</p>                        " +
                "   <div style='color : black'> Kind regards,</br></br ></div >                 <div style='color : black'>EasyMed</br></br ></br ></div >" +
                " </br > <div><img src='cid:logoImage' alt='Logo' style='position: absolute;  width: 100px; height: auto;' /></div>     " +
                "<div class='footer'>         <p>This is an automated message, please do not reply to this email.</p>     </div> </div> </body ></html >";
    }

    public static String getCancelAppointmentDtlToHospital(SchedulerHospitalTiming schedulerHospitalTiming) {

        return "   <!DOCTYPE html>  <html lang=  'en  '>  " +
                "     <head>             <meta charset='UTF-8'/>      " +
                "                            <meta name='viewport' content='width=device-width, initial-scale=1.0'>     " +
                "                                 <title>Appointment Cancelled</title>                     <style>                     " +
                "                     body {                             font - family: Arial, sans-serif;                     " +
                "                     background-color: #f4f4f4;                         margin: 0;                         padding: 20px;    " +
                "                 " +
                "                      }                         .container {                             background - color: #ffffff;      " +
                "                                    padding: 20px;                         margin: auto;                       " +
                "                   max-width: 600px;                         border-radius: 8px;                       " +
                "                   box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);         }                         h2 {                             color: #333333;         }                         p {                             line - height: 1.6;                         color: #555555;         }                         .appointment-details {                             margin: 20px 0;         }                         .appointment-details p {                             margin: 5px 0;         }     " +
                "                                     .footer {                             margin - top: 30px;                         font-size: 12px;          " +
                "                                color: #888888;         }                     </style>                 </head>   <body>     " +
                " <div class=  'container  '>          <h2>Appointment Cancelled</h2>            <p>Hello,</p>        " +
                "    <p>I hope you’re doing well. Scheduled Appointment for the following patient has been cancelled:</p>           " +
                " <div class=  'appointment-details  '>              <p><strong>Patient Name:</strong> "+schedulerHospitalTiming.getPatientName()+"</p>             " +
                " <p><strong>Preferred Date:</strong> "+schedulerHospitalTiming.getDate()+"</p>              <p><strong>Preferred Time:</strong> "+schedulerHospitalTiming.getTime()+"</p>        " +
                "      <p><strong>Doctor/Provider:</strong> Dr. "+schedulerHospitalTiming.getDocName()+"</p>              <p><strong>Department/Specialty:</strong>" +
                " "+schedulerHospitalTiming.getSpecialist()+"</p>              <p><strong>Location:</strong> "+schedulerHospitalTiming.getLocation()+"</p>          </div>         " +
                "   <p></p>        " +
                "          <p>Thank you for your assistance!</p>            <p>Best Regards,<br>EasyMed<br></p>          " +
                "  <div><img src='cid:logoImage' alt='Logo' style='position: absolute;  width: 100px; height: auto;' /></div>        " +
                "    <div class=  'footer  '>              <p>This is a request for scheduling an appointment. " +
                "Please ensure the details are correct before confirmation.</p>          </div>      </div>  </body>  </html>  ";
    }


    public static String getRescheduleAppoinmentConfmTemplate(AppointmentDetails appointmentDetails){
        return "   <html lang='en'>    " +
                "     <head>             <meta charset='UTF-8'>     " +
                "            <meta name='viewport' content='width=device-width, initial-scale=1.0'>    " +
                "                 <title>Appointment Rescheduled</title>                     <style>                    " +
                "     body {                             font - family: Arial, sans-serif;                    " +
                "     background-color: #f4f4f4;                         margin: 0;                         padding: 20px;   " +
                "" +
                "      }                         .container {                             background - color: #ffffff;     " +
                "                    padding: 20px;                         margin: auto;                      " +
                "   max-width: 600px;                         border-radius: 8px;                      " +
                "   box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);         }                         h2 {                             color: #333333;         }                         p {                             line - height: 1.6;                         color: #555555;         }                         .appointment-details {                             margin: 20px 0;         }                         .appointment-details p {                             margin: 5px 0;         }    " +
                "                     .footer {                             margin - top: 30px;                         font-size: 12px;         " +
                "                color: #888888;         }                     </style>                 </head>                 <body> " +
                "                    <div class='container'>                         <h2>Your EasyMed Appointment has been Rescheduled to "+appointmentDetails.getDate() +" " + appointmentDetails.getTime()+"</h2>                          <p>Dear "+appointmentDetails.getPatientName()+",</p>                         " +
                " <p>Thank you for booking your appointment with "+ appointmentDetails.getHospitalName()+". We are pleased to confirm your appointment as follows:</p>   " +
                "                       <div class='appointment-details'>                             <p><strong>Booking ID:</strong> "+appointmentDetails.getBookingId()+"</p>   <p><strong>Date:</strong> "+appointmentDetails.getDate()+"</p>  " +
                "                           <p><strong>Time:</strong> "+appointmentDetails.getTime()+"</p>                         " +
                "    <p><strong>Doctor/Provider:</strong> Dr. "+appointmentDetails.getDocName()+"</p>                           " +
                "  <p><strong>Department/Specialty:</strong> "+appointmentDetails.getSpecialist()+"</p>                           " +
                "  <p><strong>Location:</strong> "+appointmentDetails.getLocation()+"</p>                         </div>       " +
                "                   <p>Please arrive 15 minutes prior to your scheduled appointment time to complete any necessary paperwork. Remember to bring your insurance card and a valid photo ID.</p>         " +
                "                 <p>If you have any questions or need to reschedule, please contact us at 044 - 24356282 or email EasyMed@gmail.com.</p>  " +
                "                        <p>We look forward to seeing you soon!</p>                        " +
                "   <div style='color : black'> Kind regards,</br></br ></div >                 <div style='color : black'>EasyMed</br></br ></br ></div >" +
                " </br > <div><img src='cid:logoImage' alt='Logo' style='position: absolute;  width: 100px; height: auto;' /></div>     " +
                "<div class='footer'>         <p>This is an automated message, please do not reply to this email.</p>     </div> </div> </body ></html >";
    }

    public static String getRescheduleAppoinmentConfmDoctorTemplate(AppointmentDetails appointmentDetails){
        return "   <!DOCTYPE html>  <html lang=  'en  '>  " +
                "     <head>             <meta charset='UTF-8'/>      " +
                "                            <meta name='viewport' content='width=device-width, initial-scale=1.0'>     " +
                "                                 <title>Appointment Rescheduling Request</title>                     <style>                     " +
                "                     body {                             font - family: Arial, sans-serif;                     " +
                "                     background-color: #f4f4f4;                         margin: 0;                         padding: 20px;    " +
                "                 " +
                "                      }                         .container {                             background - color: #ffffff;      " +
                "                                    padding: 20px;                         margin: auto;                       " +
                "                   max-width: 600px;                         border-radius: 8px;                       " +
                "                   box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);         }                         h2 {                             color: #333333;         }                         p {                             line - height: 1.6;                         color: #555555;         }                         .appointment-details {                             margin: 20px 0;         }                         .appointment-details p {                             margin: 5px 0;         }     " +
                "                                     .footer {                             margin - top: 30px;                         font-size: 12px;          " +
                "                                color: #888888;         }                     </style>                 </head>   <body>     " +
                " <div class=  'container  '>          <h2>Appointment Rescheduling Request</h2>            <p>Hello,</p>        " +
                "    <p>I hope you’re doing well. Please reschedule an appointment for the following patient:</p>           " +
                " <div class=  'appointment-details  '>              <p><strong>Patient Name:</strong> "+appointmentDetails.getPatientName()+"</p>             " +
                " <p><strong>Preferred Date:</strong> "+appointmentDetails.getDate()+"</p>              <p><strong>Preferred Time:</strong> "+appointmentDetails.getTime()+"</p>        " +
                "      <p><strong>Doctor/Provider:</strong> Dr. "+appointmentDetails.getDocName()+"</p>              <p><strong>Department/Specialty:</strong>" +
                " "+appointmentDetails.getSpecialist()+"</p>              <p><strong>Location:</strong> "+appointmentDetails.getLocation()+"</p>          </div>         " +
                "   <p>Please confirm the appointment or provide alternative options if the preferred slot is unavailable. Let me know once the appointment is booked.</p>        " +
                "          <p>Thank you for your assistance!</p>            <p>Best Regards,<br>EasyMed<br></p>          " +
                "  <div><img src='cid:logoImage' alt='Logo' style='position: absolute;  width: 100px; height: auto;' /></div>        " +
                "    <div class=  'footer  '>              <p>This is a request for scheduling an appointment. " +
                "Please ensure the details are correct before confirmation.</p>          </div>      </div>  </body>  </html>  ";
    }
}
