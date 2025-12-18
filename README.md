# DRAGOLANDIA
## Introducción
El proyecto **Dragolandia** está diseñado como una herramienta didáctica para practicar patrones de diseño como Model-View-Model (MVM) y el uso de frameworks como Hibernate. A través de un entorno de fantasía, los desarrolladores pueden aprender a estructurar aplicaciones, gestionar bases de datos y aplicar buenas prácticas de programación.
## Análisis

### Diagrama de clases

```mermaid
classDiagram
direction TB
    class Mago {
	    -int id
	    -String nombre
        -int vida
        -int nivelMagia 
	    +lanzarHechizo()
    }

    class Monstruo {
	    -int id
        -String nombre
        -int vida
        -enum tipo
	    +atacar()
    }

    class Bosque {
	    -int id
        -String nombre
        -int nivelPeligro
        -Monstruo monstruoJefe
	    +mostrarJefe()
        +cambiarJefe()
    }

    class Dragon {
	    -int id
        -String nombre
        -int intensidadFuego
        -int resistencia
        -Bosque bosque
	    +exhalar(Monstruo monstruo)
    }

    class Hechizo {
	    -int id
	    -int danho
	    +aplicarEfecto(Object objetivo)
    }

    class BolaDeFuego {
	    +aplicarEfecto(Object objetivo)
    }

    class BolaDeNieve {
	    +aplicarEfecto(Object objetivo)
    }

    class Rayo {
	    +aplicarEfecto(Object objetivo)
    }

    Hechizo <|-- BolaDeFuego
    Hechizo <|-- BolaDeNieve
    Hechizo <|-- Rayo
    Bosque <--> Dragon
```


## Diseño 



### Diagrama entidad-relacion

```mermaid
erDiagram
    DRAGON {
        int id
        String nombre
        int intensidadFuego
        int resistencia
        int bosque_id
    }

    BOSQUE {
        int id
        String nombre
        int nivelPeligro
        int monstruoJefe_id
    }

    MONSTRUO {
        int id
        String nombre
        int vida
        String tipo
    }

    HECHIZO {
        int id
        int danho
    }

    BOLADEFUEGO {
        int hechizo_id
    }

    BOLADENIEVE {
        int hechizo_id
    }

    RAYO {
        int hechizo_id
    }

    DRAGON ||--o{ BOSQUE : "habita en"
    BOSQUE ||--o{ MONSTRUO : "contiene"
    HECHIZO ||--|| BOLADEFUEGO : "es un tipo de"
    HECHIZO ||--|| BOLADENIEVE : "es un tipo de"
    HECHIZO ||--|| RAYO : "es un tipo de"
```