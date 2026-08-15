package turfify.com;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class bookingService {

    private final bookingRepo bookingRepo;
    private final emailService emailService;

    public bookingService(
            bookingRepo bookingRepo,
            emailService emailService) {

        this.bookingRepo = bookingRepo;
        this.emailService = emailService;
    }

    public booking createBooking(booking booking) {

        boolean alreadyBooked =
                bookingRepo.existsByTurfIdAndBookingDateAndTimeSlot(
                        booking.getTurfId(),
                        booking.getBookingDate(),
                        booking.getTimeSlot()
                );

        if (alreadyBooked) {
            throw new RuntimeException("This time slot is already booked");
        }

        booking savedBooking = bookingRepo.save(booking);

        emailService.sendBookingEmail(savedBooking);

        return savedBooking;
    }

    public List<booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public List<booking> getBookingsByTurfAndDate(
            int turfId,
            LocalDate bookingDate) {

        return bookingRepo.findAll().stream()
                .filter(b ->
                        b.getTurfId() == turfId &&
                        b.getBookingDate().equals(bookingDate))
                .toList();
    }

    public List<String> getAvailableSlots(int turfId, LocalDate date) {

        List<String> allSlots = List.of(
                "06:00 PM - 07:00 PM",
                "07:00 PM - 08:00 PM",
                "08:00 PM - 09:00 PM",
                "09:00 PM - 10:00 PM"
        );

        List<booking> bookings =
                bookingRepo.findAll().stream()
                        .filter(b ->
                                b.getTurfId() == turfId &&
                                b.getBookingDate().equals(date))
                        .toList();

        List<String> bookedSlots = bookings.stream()
                .map(booking::getTimeSlot)
                .toList();

        return allSlots.stream()
                .filter(slot -> !bookedSlots.contains(slot))
                .toList();
    }
}