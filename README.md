# Képnézegető program specifikáció

 authors: Ördög Csaba, Tóth György, Ambrus Attila

## Rövid leírás:
Ez a program egy általános célú képnézegető Javában megvalósítva.
A képek megnyitása, valamint azok egyszerű manipulációjára képes.
A program platformfüggetlen lesz, ami azt jelenti, hogy futtatható lesz a következő operációs rendszereken:
- Windows
- Linux
- Mac OS

## Leírás

A nemKepnezegető képek között tudunk böngészni, valamint azokat megtekinteni a teljes felbontásukban.
A menü opciók:

File menüpont alatt:
- Megnyitás:
    Ennek a gombnak a megnyomásával megjelenik egy file kiválasztó felület ahol egy, több vagy akár egész mappát meg tudunk nyitni. A megjelenített file kiválasztóban csak a támogatott formátumú képeket lehet majd kiválasztani. Ha rányomunk a megnyitás gombra ezután a nemKépnézegető betölti az adott képeket a képernyőbe, ahol azok között váltani tudunk. 
- Mentés:
    Ez a funkció csak akkor választható ha az adott képen valami változtatást hajtunk végre. Ez lehet bármilyen transzformáció (pl. képelforgatás, kivágás stb.)
- Kép információ:
    Itt a képről található összes információ megtalálható: méret, felbontás, file név, kiterjeszét, készítés dátuma, módosítás dátuma valamint a színkódolása.

Szerkesztés menüpont alatt:
- Közelítés gombbal a kép méretét növelhetjük meg gombnyomásonként 10 százalékkal.
- Távolítás gombbal a kép méretét kicsinyíthetjük gombnyomásonként 10 százalékkal.
- Forgatás
    - 90°-al (a képet 90° fokkal jobbra forgatja)
    - 180°-al (a képet 180° fokkal jobbra forgatja)
    - 270°-al (a képet 270° fokkal jobbra forgatja)

- Kivágás gomb megnyomása után megjelenik egy négyzet aminek méretezésével beállíthatjuk a kívánt méretet. Hogy ezt az átméretezést véglegesíthessük egy rá kell nyomni a pipára, ami a menüpontok alatt fog megjelenni.
- Tükrözés:
    - Tükrözés vízszintesen (A képet a közepépső vízszintes átló mentén tükrözzük)
    - Tükrözés átlósan (A képet a közepépső függőleges átló mentén tükrözzük)
- Felbontás módosítása:
    Ez az opció a kép méretarányait megtartva a képnek a felbontását változtatja. Ez hasznos lehet, ha van egy túl nagy méretű képünk, és esetleg meg van szabva, hogy mekkora mérettel rendelkezhet maximum, akkor ez a funkció igazán hasznos tud lenni.

A felhaszálói felület:

    A fő cél a kép megjelenítése, ebben a minimalista felhasználói felület sokat segít, hogy igazán a képre tudjunk fókuszálni.
    A képernyó alján található egy balra mutató áttetsző nyíl, valamint egy jobbra mutató áttetsző nyíl. Ezeknek a megnyomásával a már megnyitott képek között tudunk mozogni. Egyszerre egy képet ugrunk.
    Ha egy kép sincs megnyitva, akkor a kép helyén ki lesz írva, hogy nincs kép megnyitva, amire ha duplán rákattintunk el fog indulni a file kiválasztó ablak.

    A menüpontok alatt a képernyő tetején megjelenik a kép neve is, hogy pontosan tudhassuk melyik képet nézzük éppen.


A támogatott file formátumok:
- jpg
- png
- bmp
- gif

További file formátum támogatása a jövőben várható!

## A fejlesztőknek
    Java jdk-11
    JavaFX

A program bővíthető lesz Reflection segítségével.