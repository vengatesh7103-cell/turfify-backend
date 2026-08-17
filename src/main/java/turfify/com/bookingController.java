package turfify.com;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = {
	    "http://localhost:5173",
	    "https://turfify-frontend.onrender.com"
	})

public class bookingController {

    private final bookingService bookingService;

    public bookingController(bookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public booking createBooking(@RequestBody booking booking) {
        return bookingService.createBooking(booking);
    }
    

    @GetMapping
    public List<booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/turf/{turfId}")
    public List<booking> getBookingsByTurfAndDate(
            @PathVariable int turfId,
            @RequestParam LocalDate date) {

        return bookingService.getBookingsByTurfAndDate(
                turfId,
                date
        );
    }
    @GetMapping("/available-slots")
    public List<String> getAvailableSlots(
            @RequestParam int turfId,
            @RequestParam LocalDate date) {

        return bookingService.getAvailableSlots(turfId, date);
    }
}