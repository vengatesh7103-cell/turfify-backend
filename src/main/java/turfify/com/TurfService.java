package turfify.com;


import java.util.List;

import org.springframework.stereotype.Service;
import turfify.com.TurfRepo;

@Service
public class TurfService {



	    private final TurfRepo turfRepository;

	    public TurfService(TurfRepo turfRepository) {
	        this.turfRepository = turfRepository;
	    }

	    public List<Turf> getAllTurfs() {
	        return turfRepository.findAll();
	    }

	    public Turf getTurfById(int id) {
	        return turfRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Turf not found"));
	    }

	    public Turf addTurf(Turf turf) {
	        return turfRepository.save(turf);
	    }
	}

