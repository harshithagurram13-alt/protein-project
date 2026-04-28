package app;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/protein")
public class ProteinController {

    @GetMapping("/structure/{id}")
    public ResponseEntity<String> getStructure(@PathVariable String id) throws Exception {
        String url = "https://files.rcsb.org/download/" + id.toUpperCase() + ".pdb";
        java.net.URL rcsb = new java.net.URL(url);
        java.util.Scanner scanner = new java.util.Scanner(rcsb.openStream()).useDelimiter("\\A");
        String pdb = scanner.hasNext() ? scanner.next() : "";
        return ResponseEntity.ok()
            .header("Content-Type", "text/plain")
            .body(pdb);
    }

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
