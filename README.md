# Currency Rates Study Project

This study project retrieves currency rates from the **NBP API**:  
➡️ [https://api.nbp.pl/](https://api.nbp.pl/)

## 📂 Module Structure

The project follows a **modular architecture** to enable **performant builds**, separating common Android components into a **shared module**.

### 🔹 **Modules Overview**
- **`common (kotlin)`** – Contains:
  - highlighting strategy

- **`common (android)`** – Contains:
    - Extensions
    - Design system
    - UI components
    - Navigation

- **`data`** – Handles:
    - Data models
    - Data sources
    - Networking

- **`domain`** – Manages:
    - Repositories
    - Use-cases

- **`features`** – Includes:
    - Specific app features & screens

- **`app`** – The main **Android module**

## 🚀 **Tech Stack**
The application is built using:
- **Jetpack Compose** for UI
- **Coroutines** for concurrency

## ✅ **Extras**
- 🧪 **Unit tests** in the `common` (**Kotlin**) module, domain use-cases and repositories, feature view-models
- 📸 **Screenshot tests** using Jetpack in the `common` (**Android**) module  

### Image - Currency List
![Description of Image 1](./readme/Screenshot_1.png)

### Image - Currency Details
![Description of Image 2](./readme/Screenshot_2.png)