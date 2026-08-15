package turfify.com;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/turfs")
@CrossOrigin(origins = "http://localhost:5173")
public class TurfController {

    private final TurfService turfService;

    public TurfController(TurfService turfService) {
        this.turfService = turfService;
    }

    @GetMapping
    public List<Turf> getAllTurfs() {
        return turfService.getAllTurfs();
    }

    @GetMapping("/{id}")
    public Turf getTurfById(@PathVariable int id) {
        return turfService.getTurfById(id);
    }

    @PostMapping
    public Turf addTurf(@RequestBody Turf turf) {
        return turfService.addTurf(turf);
    }
}