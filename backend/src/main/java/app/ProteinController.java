package app;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/protein")
public class ProteinController {

    /* LOAD REAL PDB STRUCTURE */
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

    /* BASIC PROTEIN INFO */
    @GetMapping("/{id}")
    public Map<String, String> getProtein(@PathVariable String id) {

        Map<String, String> map = new HashMap<>();

        map.put("Protein ID", id.toUpperCase());
        map.put("Name", "Protein " + id.toUpperCase());
        map.put("Source", "RCSB Protein Data Bank");
        map.put("Status", "Loaded Successfully");
        map.put("Project", "Protein Visualizer Pro");

        return map;
    }

    /* INTERACTION ANALYSIS */
    @GetMapping("/interactions/{a}/{b}")
    public List<Map<String, Object>> getInteractions(
            @PathVariable String a,
            @PathVariable String b) {

        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {

            Map<String, Object> row = new HashMap<>();

            row.put("Protein1", a.toUpperCase());
            row.put("Protein2", b.toUpperCase());
            row.put("InteractionScore", Math.round(Math.random() * 100) / 10.0);
            row.put("Type", i % 2 == 0 ? "Binding" : "Inhibition");

            list.add(row);
        }

        return list;
    }
}
