US01: Als Autofahrer ziehe ich bei der Einfahrt einen Klassen.Parkschein
um in das Klassen.Parkhaus hinein fahren zu können. 
US04: Als Autofahrer bezahle ich mein Klassen.Parkschein, 
um aus dem Klassen.Parkhaus herausfahren zu können. 

interface Klassen.Parkschein {
 Prüfen ob Schranke sich  nur öffnet wenn bezahlt
 boolean AutoHatBezahlt();
}
 
interface Klassen.Parkhaus {
 nur wenn Klassen.Parkschein gezogen wurde oder bezahlt wurde
 boolean Ein/AusfahrtGewähren();
 min/max 
 boolean ParkhausHatPlaetze();
 test auf neues Klassen.Parkschein
 ticket ParkscheinZiehen();
} 
 
 
Interface Autofahrer{
 int woIstMeinAuto();

Interface Klassen.Automat{
    Parkscheinbezahlen()