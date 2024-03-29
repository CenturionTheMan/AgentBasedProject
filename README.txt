# Agent Based Project

WPROWADZENIE DO PROJEKTU

Na planszy, o z góry ustalonym rozmiarze, znajdują się dwa rodzaje bytów: nieruchome i ruchome. Do nieruchomych należą:
- Podbazy
- Gimbazy
- Licbazy
- Uczelnie
Natomiast do ruchomych:
- Entity

Każdy Entit musi przejść przez następujący cykl: Podbus -> Gimbus -> Licbus -> Student;
Istnieją dwie dodatkowe role, odblokowywanie przy specjalnych okazjach: Patus i Debil
Gimbazy będą zmieniać swoją pozycje co ustaloną z góry liczbę rund (5).
Każdy Entit wewnątrz cyklu ma specjalne właściwości:
-> Podbus:
- Jeśli stanie obok innego Podbusa, zaczną poruszać się razem (jakby połączą się). 
  Grupa zacznie rozpadać się dopiero gdy kolejne Podbusy wejdą na pola obok Gimbaz, zamienią się w Gimbusy i odłączą się
- Jeśli Podbus lub grupa do dwóch Podbusów włącznie stanie obok Gimbusa lub Patusa, 
  każdy Podbus z danej grupy od tego momentu przez następne 2 rundy będzie poruszał się w losowym kierunku [dwa razy szybciej -> TEGO NIE MA JESZCZE]. 
  Nie może wtedy ewoluować w Gimbusa (przestraszą się).
- Jeśli Podbus lub dowolna grupa Podbusów stanie obok Studenta / Debila, przestrasza się.
  
-> Gimbus:
- Po zostaniu Gimbusem, przez pierwsze 10 rund nie może przestraszyć ani być przestraszonym przez Podbusów.
- Jeśli zauważy grupę od trzech wzwyż Podbusów, przestrasza się.
- Jeśli dwa Gimbusy staną obok siebie, mają 33% szans na zlikwidowanie się nawzajem (znikają z mapy) i 33% na zostanie Patusami (więcej w: Patus)
- Jeśli Gimbus zauważy Studenta / Debila, przestrasza się.
- Jeżeli w zasięgu jego wzroku znajdzie się Licbaza, uda się w jej strone i stanowszy obok niej zamieni się w Licbusa

-> Licbus:
- Licbus porusza się tak samo jak pozostałe Entity, jednak ma co rundę 20% szans na pozostawienie obok siebie na polu bytu nieruchomego, Egzaminu (więcej w: Patus)
- Jeśli Licbus zauważy Studenta / Debila, przestrasza się.
- Jeżeli w zasięgu jego wzroku znajdzie się Uczelnia, uda się w jej strone i stanowszy obok niej zamieni się w Studenta

-> Student:
- Porusza się losowo, aż zauważy byt nieruchomy, Piwo (więcej w: Patus). 
  Od tego momentu zbliża się do niego. 
- Każdy Student ma 10% szans, by na początku rundy stać się Debilem (więcej w: Debil)

-> Patus:
- Patusy mogą pojawić się gdy dwa Gimbusy staną na jednym polu (33% szans, więcej w: Gimbus).
- Poruszając się ma 20% szans na pozostawienie po sobie na polu Piwa.
- Aby wyjść ze stadium Patusa, musi on wejść na pole obok Egzaminu. Staje się wtedy Licbusem, a Egzamin znika.
- Patus posiada te same właściwości straszenia i bycia przestraszonym, co Gimbus.
- Jeżeli w zasięgu jego wzroku znajdzie się Egzamin, uda się w jego stronę.

-> Debil:
- Debil ma szansę (10%) stać się Student co rundę.
- Jeżeli w zasięgu jego wzroku znajdzie się Egzamin, uda się w jego stronę.
- Debil nie obcuje z polem z piwem, jak Student. Aby z powrotem stać się Studentem, musi wejść na pole z Egzaminem.

Dodatkowo jeżeli jakakolwiek jednostka wchodząc na pole na którym jest piwo lub egzamin, sprawia że ów znika.

Warunki końca symulacji: 
-> (1) Student konsumuje piwo
-> (2) Na mapie nie ma żadnego Piwa, Gimbusa, Podbusa ani Patusa
-> (3) Na mapie zostały same Patusy i nie ma na niej żadnych Egzaminów
-> (4) Na mapie nie ma żadnych Entit'ów ruchomych
-> (5) Minęło 600 rund a żaden z powyższych warunków nie został spełniony





