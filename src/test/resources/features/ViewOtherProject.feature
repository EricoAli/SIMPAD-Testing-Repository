Feature: Melihat Project Orang Lain

  Scenario: User membuka detail project dari halaman utama
    Given Saya sudah login ke aplikasi SIMPAD
    When Saya membuka menu Project
    And Saya mencari dan mengeklik project bernama "Erico's project team"
    Then Halaman detail project harus berhasil terbuka