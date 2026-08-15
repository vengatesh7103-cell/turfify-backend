package turfify.com;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class emailService {

    private final JavaMailSender mailSender;

    public emailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendBookingEmail(booking booking) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("playturfify@gmail.com");

        message.setSubject(
                "New Turfify Booking - " +
                booking.getUserName()
        );

        message.setText(
                "New Turfify Booking\n\n" +
                "Turf ID: " + booking.getTurfId() + "\n" +
                "Date: " + booking.getBookingDate() + "\n" +
                "Time Slot: " + booking.getTimeSlot() + "\n\n" +
                "Customer Details\n" +
                "Name: " + booking.getUserName() + "\n" +
                "Mobile: " + booking.getMobile()
        );

        mailSender.send(message);
    }
}