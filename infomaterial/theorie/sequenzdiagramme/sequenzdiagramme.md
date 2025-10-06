---
title: Sequenzdiagramme
diagram:
  engine:
    plantuml:
      mime-type:
        application/pdf: true
---

```{.java .cb-run}

```




```{.plantuml}
autoactivate on  
actor Akteur
  Akteur ->> Student : greet

  Student ->> Student : getGreeting

  

  return greeting
```
