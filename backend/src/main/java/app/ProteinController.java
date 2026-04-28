package app;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/protein")
public class ProteinController {

    @GetMapping("/interactions/{a}/{b}")
    public List<Map<String,Object>> getInteractions(
        @PathVariable String a,
        @PathVariable String b) {
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Map<String,Object> m = new HashMap<>();
            m.put("Protein1", a.toUpperCase());
            m.put("Protein2", b.toUpperCase());
            m.put("InteractionScore", Math.round(Math.random() * 100) / 10.0);
            m.put("Type", i % 2 == 0 ? "Binding" : "Inhibition");
            list.add(m);
        }
        return list;
    }

    @GetMapping("/{id}")
    public Map<String,String> getProtein(@PathVariable String id) {
        Map<String,String> map = new HashMap<>();
        map.put("Protein ID", id.toUpperCase());
        map.put("Name", "Protein " + id.toUpperCase());
        map.put("Status", "Loaded Successfully");
        return map;
    }
}
