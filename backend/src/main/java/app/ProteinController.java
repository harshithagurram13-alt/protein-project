package app;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/protein")
public class ProteinController {

    /* STRUCTURE */
    @GetMapping("/structure/{id}")
    public ResponseEntity<String> getStructure(@PathVariable String id) throws Exception {
        String url = "https://files.rcsb.org/download/" + id.toUpperCase() + ".pdb";

        Scanner scanner = new Scanner(new URL(url).openStream()).useDelimiter("\\A");
        String pdb = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        return ResponseEntity.ok()
                .header("Content-Type", "text/plain")
                .body(pdb);
    }

    /* SEARCH BY NAME */
    @GetMapping("/search")
    public ResponseEntity<String> search(@RequestParam String q) throws Exception {

        String url =
                "https://search.rcsb.org/rcsbsearch/v2/query?json=" +
                URLEncoder.encode(
                        "{ \"query\": { \"type\": \"terminal\", \"service\": \"text\", \"parameters\": { \"value\": \"" + q + "\" } }, \"return_type\": \"entry\" }",
                        StandardCharsets.UTF_8
                );

        Scanner scanner = new Scanner(new URL(url).openStream()).useDelimiter("\\A");
        String json = scanner.hasNext() ? scanner.next() : "{}";
        scanner.close();

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(json);
    }

    /* REAL STATS */
    @GetMapping("/stats/{id}")
    public ResponseEntity<String> stats(@PathVariable String id) throws Exception {

        String url = "https://data.rcsb.org/rest/v1/core/entry/" + id.toUpperCase();

        Scanner scanner = new Scanner(new URL(url).openStream()).useDelimiter("\\A");
        String json = scanner.hasNext() ? scanner.next() : "{}";
        scanner.close();

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(json);
    }

    /* INTERACTIONS */
    @GetMapping("/interactions/{a}/{b}")
    public List<Map<String, Object>> getInteractions(
            @PathVariable String a,
            @PathVariable String b) {

        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Map<String, Object> m = new HashMap<>();
            m.put("Protein1", a.toUpperCase());
            m.put("Protein2", b.toUpperCase());
            m.put("InteractionScore", Math.round(Math.random() * 100) / 10.0);
            m.put("Type", i % 2 == 0 ? "Binding" : "Inhibition");
            list.add(m);
        }

        return list;
    }

    /* BASIC INFO */
    @GetMapping("/{id}")
    public Map<String, String> getProtein(@PathVariable String id) {
        Map<String, String> map = new HashMap<>();
        map.put("Protein ID", id.toUpperCase());
        map.put("Status", "Loaded Successfully");
        return map;
    }
}
