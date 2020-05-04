US01: Als Autofahrer ziehe ich bei der Einfahrt einen Parkschein
um in das Parkhaus hinein fahren zu können. 
US04: Als Autofahrer bezahle ich mein Parkschein, 
um aus dem Parkhaus herausfahren zu können. 

interface Parkschein {
 Prüfen ob Schranke sich  nur öffnet wenn bezahlt
 boolean AutoHatBezahlt();
}
 
interface Parkhaus {
 nur wenn Parkschein gezogen wurde oder bezahlt wurde
 boolean Ein/AusfahrtGewähren();
 min/max 
 boolean ParkhausHatPlaetze();
 test auf neues Parkschein
 ticket ParkscheinZiehen();
} 
 
 
Interface Autofahrer{
 int woIstMeinAuto();

Interface Automat{
    Parkscheinbezahlen()