package tr.edu.medipol.yazilimaraclarifinal.webServisFinal;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/urun")
public class FinalWebServis {

    public record Urun(String ad, String fiyat) {};

    private static final List<Urun> URUN_LISTESI = new ArrayList<>();
    static {
        URUN_LISTESI.add(new Urun("Domates", "34"));
        URUN_LISTESI.add(new Urun("Salatalık", "27"));
        URUN_LISTESI.add(new Urun("Bebek Bezi", "400"));
    }
   
    @GetMapping("/")
    public List<Urun> listele(){
        return URUN_LISTESI;
    }
   
    @GetMapping("/{ad}")
    public Urun bul(@PathVariable String ad){
        for(Urun urun: URUN_LISTESI) {
            if(urun.ad().equalsIgnoreCase(ad)) {
                return urun;
            }
        }
        return null;
    }
   
    @DeleteMapping("/{ad}")
    public boolean sil(@PathVariable String ad) {
        Urun urun = bul(ad);
        if(urun != null) {
            URUN_LISTESI.remove(urun);
            return true;
        }
        return false;
    }
   
    @PostMapping("/")
    public Urun ekle(@RequestBody Urun urun) {
        URUN_LISTESI.add(urun);
        return urun;
    }
}