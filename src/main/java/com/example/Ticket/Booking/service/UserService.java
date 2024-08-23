package com.example.Ticket.Booking.service;


import com.example.Ticket.Booking.model.*;

public interface UserService {
    void newUser(User user);

    User userLogin(Login login, OTP otp);

    User forgotUserName(ForgotRequest forgotRequest);

    ForgotPasswordRequest forgotPassword(ForgotPasswordRequest forgotRequest);

    String resetPassword(ResetPassword resetPassword);

    void verifyOtp(OTP otp);
}
