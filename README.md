
## Identification du Projet

Ce projet implémente un **module de transformation ETL** (Extract-Transform-Load) permettant de connecter le système ABC (entreprise de modélisme) au système DDD (entreprise de quincaillerie).

### Contexte

- **Système source (ABC)**: Génère des rapports au format JSON pour 3 produits
- **Système cible (DDD)**: Accepte des objets implémentant l'interface `ArticleExterneInterface`
- **Problème**: Incompatibilité entre les formats JSON et l'interface objet
- **Solution**: Pattern Adaptateur pour transformer les données

### Les 3 Produits

1. **Super colle** - Adhésif pour modélisme
2. **Couteau de précision** - Outil de coupe
3. **Tablette humide** - Accessoire de peinture

DDD définit ces 3 produits sous le terme générique **"Ensemble de bricolage ABC"**.

---

## Architecture du Projet

### Pattern Adaptateur (Adapter)

Le projet utilise le **pattern Adapter** pour résoudre l'incompatibilité entre les deux systèmes:

```
SystemeABC (JSON) → Adaptateur → ArticleExterneInterface → SystemeDDD
```

**Composants**:
- **Interface cible**: `ArticleExterneInterface`
- **Adaptateurs**: `AdaptateurSuperColle`, `AdaptateurCouteauPrecision`, `AdaptateurTabletteHumide`
- **Adaptés**: Chaînes JSON provenant de `SystemeABC`

### Pattern Singleton

La classe `Logger` utilise le pattern Singleton pour assurer une **instance unique** de journalisation dans toute l'application.

**Caractéristiques**:
- Constructeur privé
- Méthode statique `getInstance()`
- Journalise toutes les opérations, traitements et exceptions

---

## Structure des Packages

```
src/main/java/
├── Main.java                          (Point d'entrée)
├── utils/
│   └── Logger.java                    (Singleton pour journalisation)
├── abc/
│   └── SystemeABC.java                (Système source - mock JSON)
├── transformation/
│   ├── ArticleExterneInterface.java   (Interface acceptée par DDD)
│   ├── JSONParser.java                (Parser manuel)
│   ├── AdaptateurSuperColle.java      (Adapter 1/3)
│   ├── AdaptateurCouteauPrecision.java (Adapter 2/3)
│   └── AdaptateurTabletteHumide.java  (Adapter 3/3)
└── ddd/
    ├── EnsembleBricolageABC.java      (Classe abstraite)
    ├── SuperColle.java                (Produit concret DDD)
    ├── CouteauPrecision.java          (Produit concret DDD)
    ├── TabletteHumide.java            (Produit concret DDD)
    └── SystemeDDD.java                (Système cible)
```

**Total**: 13 classes Java

---

## Parsing JSON Manuel (SANS API Externe)

### Confirmation Importante

Le parsing JSON est réalisé **MANUELLEMENT** sans aucune API externe (pas de Jackson, Gson, ou autre bibliothèque JSON).

### Implémentation

La classe `JSONParser` utilise **uniquement** des classes du JDK standard:
- `java.util.regex.Pattern` (expressions régulières - JDK depuis Java 1.4)
- `java.util.regex.Matcher` (correspondance de patterns)
- `String.substring()`, `trim()`, `replace()` (manipulation de chaînes)

**Méthode de parsing**:
```java
Pattern pattern = Pattern.compile("\"([^\"]+)\"\\s*:\\s*(\"([^\"]*)\"|([^,}]+))");
Matcher matcher = pattern.matcher(cleaned);
while (matcher.find()) {
    String key = matcher.group(1);
    String value = matcher.group(3) != null ? matcher.group(3) : matcher.group(4).trim();
    result.put(key, value);
}
```

Cette approche extrait les paires clé-valeur du JSON en utilisant des expressions régulières, sans dépendance externe.

---

## Flux ETL (Extract-Transform-Load)

### 1. EXTRACTION

Le système ABC génère des rapports JSON via la méthode:
```java
String genererRapport(int idProduit)
```

**Produits mockés**:
- ID 1: Super colle
- ID 2: Couteau de précision
- ID 3: Tablette humide

### 2. TRANSFORMATION

Les adaptateurs transforment le JSON en objets `ArticleExterneInterface`:
- `AdaptateurSuperColle` parse le JSON et implémente l'interface
- `AdaptateurCouteauPrecision` parse le JSON et implémente l'interface
- `AdaptateurTabletteHumide` parse le JSON et implémente l'interface

### 3. CHARGEMENT

Le système DDD importe les articles via la méthode:
```java
void importExtArt(ArticleExterneInterface article)
```

Les objets sont transformés en instances de:
- `SuperColle` (extends `EnsembleBricolageABC`)
- `CouteauPrecision` (extends `EnsembleBricolageABC`)
- `TabletteHumide` (extends `EnsembleBricolageABC`)

---

## Compilation et Exécution

### Prérequis

- Java JDK 8 ou supérieur
- Terminal / Ligne de commande

### Méthode 1: Scripts Automatiques

**Compilation**:
```bash
./compile.sh
```

**Exécution**:
```bash
./run.sh
```

### Méthode 2: Commandes Manuelles

**Compilation** (depuis la racine du projet):
```bash
mkdir -p bin
javac -d bin -sourcepath src/main/java src/main/java/Main.java src/main/java/utils/*.java src/main/java/abc/*.java src/main/java/transformation/*.java src/main/java/ddd/*.java
```

**Exécution**:
```bash
cd bin
java Main
```

---

## Résultat Attendu

Lors de l'exécution, vous observerez:

1. **Logs de démarrage** avec timestamps
2. **Extraction** des 3 produits du système ABC (logs de génération JSON)
3. **Transformation** via les adaptateurs (logs de parsing détaillés)
4. **Chargement** dans le système DDD (logs d'importation)
5. **Affichage du catalogue** DDD avec les 3 produits:
   - Super colle (4.99$)
   - Couteau de précision (9.49$)
   - Tablette humide (40.00$)

**Format des logs**:
```
[2026-02-22 HH:mm:ss] [NIVEAU] Message
```
---

## Diagramme UML

Le diagramme de classe complet est disponible dans le fichier `diagramme_uml_final.puml`.



