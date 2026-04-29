// Search by name
@GetMapping("/search")
public ResponseEntity<String> searchProteins(@RequestParam String q) throws Exception {
    String searchUrl = "https://search.rcsb.org/rcsbsearch/v2/query";
    String body = "{\"query\":{\"type\":\"terminal\",\"service\":\"full_text\",\"parameters\":{\"value\":\"" + q + "\"}},\"return_type\":\"entry\",\"request_options\":{\"paginate\":{\"start\":0,\"rows\":8}}}";

    java.net.URL url = new java.net.URL(searchUrl);
    java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json");
    conn.setDoOutput(true);
    conn.getOutputStream().write(body.getBytes());

    java.util.Scanner sc = new java.util.Scanner(conn.getInputStream()).useDelimiter("\\A");
    String result = sc.hasNext() ? sc.next() : "{}";
    return ResponseEntity.ok().header("Content-Type","application/json").body(result);
}

// Fetch stats for a protein
@GetMapping("/stats/{id}")
public ResponseEntity<String> getStats(@PathVariable String id) throws Exception {
    String gql = "{\"query\":\"{entry(entry_id:\\\"" + id + "\\\"){struct{title}rcsb_entry_info{resolution_combined polymer_entity_count_protein deposited_atom_count deposited_modeled_polymer_monomer_count experimental_method organism_scientific_name}rcsb_accession_info{deposit_date}polymer_entities{rcsb_polymer_entity{pdbx_description}}}}\"}";

    java.net.URL url = new java.net.URL("https://data.rcsb.org/graphql");
    java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json");
    conn.setDoOutput(true);
    conn.getOutputStream().write(gql.getBytes());

    java.util.Scanner sc = new java.util.Scanner(conn.getInputStream()).useDelimiter("\\A");
    String result = sc.hasNext() ? sc.next() : "{}";
    return ResponseEntity.ok().header("Content-Type","application/json").body(result);
}
