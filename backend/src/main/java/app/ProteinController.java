package app;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/protein")
public class ProteinController {

@GetMapping("/{id}")
public Map<String,String> getProtein(@PathVariable String id){

Map<String,String> map=new HashMap<>();
map.put("Protein ID",id.toUpperCase());
map.put("Name","Protein "+id.toUpperCase());
map.put("Status","Loaded Successfully");

return map;
}

@GetMapping("/interactions/{a}/{b}")
public List<Map<String,Object>> getInteractions(
@PathVariable String a,
@PathVariable String b){

List<Map<String,Object>> list=new ArrayList<>();

for(int i=1;i<=5;i++){
Map<String,Object> m=new HashMap<>();
m.put("Protein1",a.toUpperCase());
m.put("Protein2",b.toUpperCase());
m.put("Strength",Math.random()*10);
list.add(m);
}

return list;
}
}
