<div align="center">
  
# Currency Layer App

***Programma per scommettere sul tasso di cambio di coppie di valute.***
  
</div>

## Indice
* [Introduzione](#introduzione)
* [Rotte](#rotte)	
	* [Parametri](#parametri)
	* [Formato restituito](#formato-restituito)
	* [Esempi di stampa](#esempi-di-stampa)
* [Struttura programma](#struttura-programma)
* [Test](#test)
* [Come usarlo](#come-usarlo)
* [Avvertenze](#avvertenze)
* [Autori](#autori)

## Introduzione

***Currency Layer App*** consiste in un programma sviluppato in Java con l'uso del framework Spring Boot basato sul funzionamento dell'API presente al seguente link: "https://currencylayer.com/".
Da quest'ultima è possibile ottenere diverse informazioni riguardanti l'andamento dell'exchange rate di coppie di valute in relazione tra loro.

La particolarità di Currency Layer App è l'implementazione di un "servizio di scommesse" che permette di scommettere sull'andamento di una valuta rispetto ad un'altra e, se la valuta sulla quale si scommette aumenta il proprio valore rispetto alla valuta di riferimento il giorno seguente la scommessa, allora l'utente avrà vinto, altrimenti avrà perso.

L'utente può richiedere, inoltre, le statistiche relative all'exchange rate di una coppia di valute (la cui source è sempre *USD*) tra cui la media, la varianza, il suo massimo e il suo minimo, da utilizzare come riferimento per la scommessa.
Infine sono implementati anche dei filtri che permettono all'utente di ricevere informazioni su una determinata coppia di valute al momento attuale o in una data passata.

> Di seguito le indicazioni riguardanti la realizzazione del progetto: 
> "sviluppare un'applicazione Java che implementi un servizio di scommesse che consente ai propri utenti di scommettere sull'andamento di un insieme di valute definite in input, vincendo o perdendo se una determinata valuta andrà ad aumentare il proprio valore oppure no, nel giorno successivo. Si utilizzino dati storici per simularne il comportamento. Effettuare statistiche sull'andamento del valore di una valuta calcolando media e varianza delle stesse. Implementare filtri per valute e periodi di valutazione dell'investimento monetario.

## Rotte

Le richieste che l'utente vuole effettuare tramite postman devono essere fatte in riferimento all'indirizzo *http://localhost:8080*.
Nel nostro programma sono state implementate diverse rotte con le quali è possibile effettuare diverse azioni.

Come prime rotte abbiamo deciso di lasciare la possibilità all'utente di ottenere le stesse informazioni che abbiamo ottenuto noi dall'API di riferimento, ovvero:

* ```/list```;
* ```/live```;
* ```/historical/{date}```.

Passiamo ora alle rotte che sono state aggiunte dopo un'analisi adeguata dei dati ricevuti dall'API:

* ```/statistics/{acronym}```;
* ```/bet```;
* ```/betResult```;
* ```/currencyFilter/{acronym}```;
* ```/historicalFilter```.


|**N°**|**Tipo**|**Rotta**|**Indirizzo**|**Descrizione**|
|------|--------|---------|-------------|---------------|
|**1**|```GET``` |```/live```|http://localhost:8080/live| Restituisce la lista degli attuali tassi di cambio|
|**2**|```GET``` |```/list```|http://localhost:8080/list| Restituisce la lista di tutte le valute presenti nel servizio |
|**3**|```GET``` |```/historical/{date}```|http://localhost:8080/historical/YYYY-MM-DD| Restituisce i tassi di cambio relativi alla data specificata |
|**4**|```GET``` |```/statistics/{acronym}```|http://localhost:8080/statistics/acronym| Restituisce le statistiche relative alla valuta specificata (media,varianza,max,min)|
|**5**|```GET``` |```/bet```|http://localhost:8080/bet?acronym1=GBP&acronym2=EUR&acronym3=MXN| Permette di piazzare una scommessa sulle valute specificate fino ad un massimo di tre|
|**6**|```GET``` |```/betResult```|http://localhost:8080/betResult |Restituisce il risultato delle scommesse precedentemente piazzate|
|**7**|```GET``` |```/currencyFilter/{acronym}```| http://localhost:8080/currencyFilter?acronym1=EUR&acronym2=GBP| Filtra la valuta chiesta in ingresso stampando le informazioni ad essa relative|
|**8**|```GET``` |```/historicalFilter```|http://localhost:8080/historicalFilter?date=2021-12-14&acronym1=GBP&acronym2=EUR| Restituisce le informazioni relative alla valuta in ingresso nella data specificata|

### Parametri

|**N°**|**Parametri**|**Tipo**|**Richiesti**|
|------|-------------|--------|-------------|
|**3**| ```date``` | *String* | *Sì* |
|**4**| ```acronym```|*String*|*Sì*|
|**5**|```acronym1,arcronym2,acronym3 ```|*String, String, String*|*Sì,No,No*|
|**7**|```acronym1,acronym2```|*String, String*|*Sì,No*|
|**8**|```date,acronym1,acronym2```|*String, String, String*|*Sì,Sì,No*|

### Formato restituito 

#### *Statistics*

``` json
{
    "Statistics": {
        "Min": 0.818495,
        "Max": 95.909247,
        "Couple": "USDAFN",
        "Average": 82.6359225,
        "Period": "2021",
        "Variance": 3366.692962893265
    }
}
```

#### *Bet*

```java
Bet based on: USDEUR with current ExchangeRate: 0.883645
Bet based on: USDGBP with current ExchangeRate: 0.753438
Bet based on: USDMXN with current ExchangeRate: 20.885204
See results at http://localhost:8080/betResult
```

#### *Bet Result*

```json
{
    "first bet": {
        "result": "You won",
        "bet on": "USDEUR",
        "yesterday rate": 0.883645,
        "today rate": 0.88406
    },
    "third bet": {
        "result": "You lost",
        "yesterday rate": 103.163942,
        "based on": "USDAFN",
        "today rate": 103.163942
    },
    "second bet": {
        "result": "You lost",
        "yesterday rate": 2.0241161E-5,
        "based on": "USDBTC",
        "today rate": 1.9953228E-5
    }
}
```

#### *Filter for currency*

``` json
{
    "filter": {
        "USDSCR": 12.785038,
        "SCR": "Seychellois Rupee",
        "USD": "United States Dollar",
        "USDINR": 75.717504,
        "INR": "Indian Rupee"
    }
}
```

#### *Filter for date*

``` json
{
    "historical": {
        "date": "2008-12-14",
        "USDEUR": 0.748763,
        "USDGBP": 0.668464
    }
}
```

### Esempi di stampa
Vediamo alcuni esempi di stampa per alcune rotte.

La rotta  ```/bet``` restituisce una Stringa dove si dichiara che le scommesse effettuate sono andate a buon fine e, in particolare, mostra all'utente l'attuale valore del tasso di cambio delle coppie su cui si è deciso di scommettere.
In aggiunta si può notare come in questa risposta viene già indicata la rotta da seguire per ottenere il risultato delle proprie scommesse.

![image](https://user-images.githubusercontent.com/91832750/146398901-d525964f-d107-4a69-8888-5e2862cbbc6e.png)

La rotta ```/betResult``` restituisce un JSONObject contenente il resoconto delle proprie scommesse e dichiarando se ognuna è stata vincente o perdente. In aggiunta viene stampato anche il tasso di cambio del giorno attuale, da poter confrontare con quello ottenuto il giorno in cui si è effettuata la scommessa.

![image](https://user-images.githubusercontent.com/91832750/146399009-ab03aba6-21da-480e-9cfd-96635ce1cf8f.png)

La rotta ```/currencyFilter``` che in ingresso chiede l'acronimo della valuta che si vuole filtrare restituisce un JSONObject contenente le informazioni relative tale valuta richiesta, tra cui il suo nome, il nome della source e il tasso di cambio della coppia.

![image](https://user-images.githubusercontent.com/91832750/146575480-f6221cf8-27a6-4dd3-9231-19edf66ed5c6.png)

La rotta ```/statistics/{acronym}``` chiede in ingresso l'acronimo della valuta della quale si vogliono conoscere le statistiche: media, varianza, massimo e minimo nel periodo di riferimento.

![image](https://user-images.githubusercontent.com/91832750/146575755-b7a2c2a7-c101-40c7-af83-c94c683638d6.png)


## Struttura programma
Per rendere più comprensibile ed organizzato il programma e tutto ciò che lo riguarda abbiamo deciso di organizzare le nostre classi in più package, così che ogni package di riferimento vada ad identificare la funzione di ogni classe presente al suo interno.
Di seguito una rapida spiegazione delle classi e dei metodi che abbiamo deciso di implementarem, ricordiamo comunque che è consultabile la [documentazione](https://github.com/sarabrusch/ProgettoEsame/tree/main/project/javadoc) (javadoc), che si trova all'interno della repository, nel caso in cui qualcuno fosse interessato ad analizzare più nei dettagli il nostro programma.

### **com.currencylayer.project**
Il primo package da cui parte tutta la nostra implementazione contiene soltando la classe ```ProjectApplication``` che è la classe responsabile dell'avvio di tutta l'applicazione Spring Boot.

### **com.currencylayer.project.controller**
In questo package, come è intuibile dal nome, è presente soltanto la classe ```CurrencyLayerController```.
Tale classe è indispensabile alla creazione del programma poiché contiene tutte le rotte che l'utente può richiedere all'indirizzo localhost:8080 e, dunque, contiene i metodi che stabiliscono il tipo di risposta che dovrà essere restituita a seguito di una chiamata.
In particolare ogni metodo del controller richiama metodi di altre classi responsabili di definire e di elaborare i dati a nostra disposizione.


### **com.currencylayer.project.exceptions**
Contiene le eccezioni personalizzate che abbiamo deciso di introdurre per il nostro programma.

```CurrencyNotFoundException``` che viene lanciata quando non si trova una corrispondenza con una valuta inserita dall'utente e, quindi, tale valuta non esisterà o sarà stata inserita in modo scorretto (esempio: FUR inesistente o eur formato errato).

```InvalidFormatDateException``` che viene lanciata quando l'utente ha inserito una data scritta nel modo sbagliato o non esistente. Esempio: 12-02-2021 (formato non corretto) o 2021-13-11 (non esistente).

### **com.currencylayer.project.filters**
Contiene l'interfaccia ```FiltersService``` necessaria a modellare i metodi che ci permettono poi di sviluppare i nostri filtri all'interno della classe ```Filters``` che implementa proprio l'interfaccia poco prima citata.

La classe ```Filters``` contiene i metodi:
* ```currencyFilter(String acronym1, String acronym2)``` che implementa un filtraggio delle valute presenti nel sistema avendo in ingresso l'acronimo delle valute di cui si stanno cercando le informazioni. Tale metodo è quello che utilizziamo per la rotta ```/currencyFilter```.
* ```historicalFilter(String date, String acronym1, String acronym2)``` implementa un filtraggio "storico", ovvero prende in ingresso una data (nel formato YYYY-MM-DD) e massimo due acronimi per stampare in uscita le informazioni storiche relative alle valute richieste nel giorno richiesto. Questo metodo viene utilizzato per la rotta ```/historicalFilter``` che usa ha come parametri la data e uno (o due) acronimi, come è possibile vedere alla tabella [parametri](#parametri).

### **com.currencylayer.project.model**
Questo package contiene le classi che vanno a modellare il problema richiesto, in particolare troviamo le classi: ```Bet```,```Currency```,```CurrencyCouple```,```CurrencyCoupleExchange```,```OurDate```,```Source```, all'interno delle quali sono presenti sia il classico costruttore necessario per definire oggetti delle relative classi, sia getters e setters delle variabili che le descrivono, ma anche un overriding del metodo ```toString()```.

All'interno di queste classi abbiamo cercato di usare il più possibile i concetti di ereditarietà acquisiti durante il corso di Programmazione a oggetti, fondamentali per un linguaggio di programmazione come Java.

### **com.currencylayer.project.service**
Il package service ci è servito per raggruppare le classi che sono responsabili del "servizio", appunto, offerto dal nostro programma, ma anche le classi che contengono metodi utili alla lettura da API, che ci hanno permesso di raccogliere e memorizzare i dati necessari, così da rielaborarli secondo necessità.

Troviamo al suo interno l'interfaccia ```CurrencyLayerService``` che viene implementata dalla classe ```CurrencyLayerServiceImpl``` all'interno della quale troviamo i metodi:

* ```getData(String word)``` che ci permette di andare a memorizzare il JSON restituito dalla chiamata all'API con rotta ```/word``` che nel nostro caso dovrà essere ```/live o /list```. Questo metodo viene infatti richiamato dal controllore per costruire le rotte omonime presenti nel nostro programma.
* ```getHistoricalQuotation(String date)``` come il precedente ci permette di memorizzare il JSON restituito dall'API ma anche di costruire la nostra omonima rotta che richiede in input una data della quale si vogliono conoscere il tassi di cambio.
* ```getCurrency(String acronym)``` sfrutta la chiamata a /list per permetterci di ottenere una corrispondenza acronimo-nome di una valuta della quale si specifica l'acronimo in input (questo metodo viene riutilizzato per l'implementazione dei filtri).
* ```getCouple(String acronym)``` sfrutta la chiamata a /live per permetterci di ottenere una corrispondenza coppia-tasso di cambio di una valuta della quale si specifica l'acronimo in input, ricordando che la source è sempre la moneta americana "USD" (questo metodo viene riutilizzato per l'implementazione dei filtri).

All'interno dello stesso package viene poi anche implementata l'interfaccia ```BetService``` dalla classe ```BetServiceImpl``` che raccoglie i metodi necessari ad implementare il servizio di scommesse, tra essi:
* ```doBet (String acronym1, String acronym2,String acronym3)``` che si occupa di gestire la memorizzazione della scommessa basandosi sugli acronimi inseriti dall'utente (il return di questo metodo viene stampato in risposta alla rotta ```/doBet```.
* ```betResult()``` va a confrontare il tasso di cambio che si aveva al momento della scommessa con quello del giorno successivo per decretare se la scommessa effettuata è vincente (se il tasso di cambio aumenta) o perdente (se il tasso di cambio diminuisce).

> Attenzione: per simulare e verificare il funzionamento delle scommesse abbiamo utilizzato dei dati scritti su file relativi a giorni passati, qualora si volesse simulare con il live basterebbe semplicemente modificare leggermente i metodi poco sopra elencati.

### **com.currencylayer.project.statistics**
Questo package contiene le classi necessarie al calcolo delle statistiche, in particolare troviamo un'interfaccia ```StatisticsService``` che definisce i metodi necessari ai nostri scopi, i quali vengono implementati dalla classe ```Statistics```.

> Attenzione: per il calcolo delle statistiche ci siamo basati su una raccolta di dati attraverso scrittura su file delle chiamate API andando ad effettuare i calcoli soltanto per il primo di ogni mese dell'anno 2021. Qualora si volesse estendere la valutazione sarebbe semplicemente necessario applicare qualche modifica all'interno dei metodi elencati di seguito.

* ```getAverage()``` va a calcolare la media della coppia source+acronym (con acronym dichiarato in input) nel periodo preso come riferimento.
* ```getVariance()``` calcola la varianza della stessa coppia di valute basandosi sul calcolo della loro media.
* ```getMax()``` restituisce il picco più alto raggiunto dal tasso di cambio della coppia in esame nel periodo di riferimento.
* ```getMin()``` restituisce il picco più basso raggiunto dal tasso di cambio della coppia in esame nel periodo di riferimento.
* ```getStatistics(String acronym)``` restituisce il JSONObject contenente i dati restituiti dai metodi elencati sopra.

### **com.currencylayer.project.utilis**
All'interno di quest'ultimo package abbiamo raccolto metodi utili alla risoluzione delle problematiche che ci si sono presentate; in particolare è presente una classe ```FileAnalysis``` contenente il metodo ```readFile(String fileName, String word)``` che ci permette di leggere il file con nome=fileName e restituisce il JSONObject letto e relativo alla key "currencies" o "quotes" a seconda del tipo di file che si va a leggere.

## Test
Per effettuare i test sul corretto funzionamento del codice abbiamo utilizzato *JUNIT*, un framework open source per effettuare testing in modo organizzato; *JUNIT* è nato per la scrittura di test unitari, ovvero test che vanno a verificare la correttezza direttamente sul codice, in ogni piccola parte.

Abbiamo implementato quattro test, in particolare:

* **Test1:** verifica della correttezza del filtro per valute attraverso il loro acronimo;
* **Test2:** verifica della correttezza della chiamata a API che permette di ottenere dati storici;
* **Test3:** verifica della correttezza del lancio dell'eccezione CurrencyNotFoundException;
* **Test4:** verifica della correttezza del lancio dell'eccezione InvalidFormatDateException.

Il codice relativo ad ogni test è visionabile [qui](https://github.com/sarabrusch/ProgettoEsame/blob/main/project/src/test/java/com/currencylayer/project/test/CurrencyLayerTest.java).

## Come usarlo
Per poter accedere al programma è necessario clonare la repository in locale utilizzando [Github Desktop](https://desktop.github.com/) oppure da terminale con il comando 

```git clone https://github.com/sarabrusch/ProgettoEsame```

Successivamente sarà possibile mandare in esecuzione il programma con un IDE (nel nostro caso è stato usato Eclipse) o direttamente da terminale. In questo modo si potranno testare le varie rotte andando a chiamare il client http://localhost:8080 da Postman o direttamente da web.


## Avvertenze
Ci è necessario sottolineare il fatto che questo programma è stato realzzato per un progetto universitario e dunque non sarà più soggetto ad aggiornamenti o miglioramenti da parte dei proprietari.
Un'ulteriore particolare di cui è bene tenere conto è che noi sviluppatori ci siamo basati sulla versione gratuita dell'API linkata in [introduzione](#introduzione), avendo avuto così accesso ad azioni limitate che ci hanno portato a dover scegliere di lavorare su file invece che direttamente da chiamata ad API, soprattutto per il calcolo di statistiche e per l'implementazione dei filtri; inoltre tali limitazioni ci hanno costretto ad utilizzare come unica source per gli exchange rate la moneta americana "USD".
Qualora qualcuno fosse interessato a svilupparne una versione più complessa acquistando l'API in questione sarebbe comunque in grado di utilizzare il nostro codice apportando semplicemente qualche modifica.

## Autori
Per quanto riguarda la suddivisione dei compiti abbiamo sempre lavorato insieme attraverso piattaforme di live streaming, dividendo in modo equo il lavoro, così da lavorare in singolo o in gruppo, quando necessario. Abbiamo comunque sempre mantenuto aperta la comunicazione e la collaborazione tra noi, usando le nozioni acquisite alle lezioni di programmazione ad oggetti o in autonomia tramite ricerche online, affiché il progetto risultasse il più consono possibile per entrambi i collaboratori.
* [Sara Bruschi](https://github.com/sarabrusch)
* [Marco Di Vita](https://github.com/marcopapero)
