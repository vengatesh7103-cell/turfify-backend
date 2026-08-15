package turfify.com;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface bookingRepo extends JpaRepository<booking, Integer> {

    boolean existsByTurfIdAndBookingDateAndTimeSlot(
            int turfId,
            LocalDate bookingDate,
            String timeSlot
    		 ); 
            List<booking> findByTurfIdAndBookingDate(
                    int turfId,
                    LocalDate bookingDate
            );
   
}