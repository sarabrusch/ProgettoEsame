# Currency Layer App

## Introduzione

*Currency Layer App* consiste in un programma sviluppato in Java con l'uso del framework Spring Boot basato sul funzionamento dell'API presente al seguente link: "https://currencylayer.com/".
Da essa è possibile ottenere diverse informazioni riguardanti l'andamento dell'exchange rate di coppie di valute in relazione tra loro.
La particolarità di Currency Layer App è l'implementazione di un "servizio di scommesse" che permette di scommettere sull'andamento di una valuta rispetto ad un'altra e, se la valuta sulla quale si scommette aumenta il proprio valore rispetto alla valuta di riferimento il giorno seguente la scommessa, allora l'utente avrà vinto, altrimenti avrà perso.
L'utente può richiedere, inoltre, le statistiche relative all'exchange rate di una coppia di valute (la cui source è sempre *USD*) tra cui la media e la varianza, da utilizzare come riferimento per la sua scommessa.
Infine sono implementati anche dei filtri che permettono all'utente di ricevere informazioni su una determinata coppia di valute.
