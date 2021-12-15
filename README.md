# Currency Layer App

## Introduzione

***Currency Layer App*** consiste in un programma sviluppato in Java con l'uso del framework Spring Boot basato sul funzionamento dell'API presente al seguente link: "https://currencylayer.com/".
Da quest'ultima è possibile ottenere diverse informazioni riguardanti l'andamento dell'exchange rate di coppie di valute in relazione tra loro.
La particolarità di Currency Layer App è l'implementazione di un "servizio di scommesse" che permette di scommettere sull'andamento di una valuta rispetto ad un'altra e, se la valuta sulla quale si scommette aumenta il proprio valore rispetto alla valuta di riferimento il giorno seguente la scommessa, allora l'utente avrà vinto, altrimenti avrà perso.
L'utente può richiedere, inoltre, le statistiche relative all'exchange rate di una coppia di valute (la cui source è sempre *USD*) tra cui la media e la varianza, da utilizzare come riferimento per la sua scommessa.
Infine sono implementati anche dei filtri che permettono all'utente di ricevere informazioni su una determinata coppia di valute.

> Di seguito le indicazioni riguardanti la realizzazione del progetto: 
> "sviluppare un'applicazione Java che implementi un servizio di scommesse che consente ai propri utenti di scommettere sull'andamento di un insieme di valute definite in input, vincendo o perdendo se una determinata valuta andrà ad aumentare il proprio valore oppure no, nel giorno successivo. Si utilizzino dati storici per simularne il comportamento. Effettuare statistiche sull'andamento del valore di una valuta calcolando media e varianza delle stesse. Implementare filtri per valute e periodi di valutazione dell'investimento monetario.

## Rotte

Le richieste che l'utente vuole effettuare tramite postman devono essere fatte in riferimento all'indirizzo *http://localhost:8080*.
Nel nostro programma sono state implementate diverse rotte con le quali è possibile effettuare diverse azioni.

Come prime rotte abbiamo deciso di lasciare la possibilità all'utente di ottenere le stesse informazioni che abbiamo ottenuto noi dall'API di riferimento, ovvero:

* Con la rotta ```/list``` e dunque la chiamata all'URL http://localhost:8080/list, è possibile ottenere la lista di tutte le valute disponibili descritte da coppie di acronimo-nome, sulle quali poi l'utente potrà andare a richiedere altre informazioni e/o a scommettere.
* Con la rotta ```/live``` è possibile ottenere la lista del valore del tasso di cambio attuale delle valute viste con la rotta precedente in riferimento alla valuta "USD" che costituisce sempre la source di riferimento in questo programma.
* Con la rotta ```/historical/{date}``` è possibile ottenere la lista dei tassi di cambio relativi ad un giorno specifico che dovrà essere indicato in ingresso dall'utente nel seguente formato: http://localhost:8080/historical/YYYY-MM-DD.

Passiamo ora alle rotte che sono state aggiunte dopo un'analisi adeguata dei dati ricevuti dall'API:

* Con la rotta ```/statistics/{acronym}``` si otterrà la lista di statistiche relative alla coppia USD+acronym richiesta dall'utente. In particolare tali statistiche comprendono il calcolo di media e varianza del tasso di cambio e restituisce anche il valore massimo e il valore minimo raggiunti nel periodo preso da noi in esame per il calcolo delle statistiche.
* Con la rotta ```/bet``` è possibile piazzare una scommessa su un massimo di tre coppie di valute (dove ricordiamo che source=USD **sempre**). La richiesta in questo caso sarà ad esempio, se volessimo scommettere sulle coppie USDGBP, USDEUR, USDBTC: http://localhost:8080/bet?acronym1=GBP&acronym2=EUR&acronym3=MXN.
* 
