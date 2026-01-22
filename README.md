Miten Compose-tilanhallinta toimii:
- Compose-tilanhallinta perustuu reaktiivisuuteen: UI päivittyy automaattisesti aina, kun siihen liittyvä tila muuttuu.
- Tila voidaan säilyttää paikallisesti remember-funktiolla tai pidempään ViewModelin avulla.

Miksi ViewModel on parempi kuin pelkkä remember:
- ViewModel säilyttää datan sovelluksen elinkaaren yli, esim. ruudun kierron yhteydessä.
  Se pitää logiikan erillään käyttöliittymästä, mikä tekee koodista selkeämpää ja helpommin ylläpidettävää.
  Composables voivat seurata ViewModelin tilaa reaktiivisesti, jolloin UI päivittyy automaattisesti muutosten yhteydessä.
