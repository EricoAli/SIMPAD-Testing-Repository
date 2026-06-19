Feature: Melihat Project Orang Lain Tanpa Login

  Scenario: Pengguna publik (belum login) melihat detail project
    # Kita panggil step dari LoginSteps yang hanya membuka URL web (tidak melakukan login)
    Given Saya berada di halaman utama SIMPAD

    # Kita panggil step dari ViewProjectSteps yang baru saja kita buat
    When Saya membuka menu Project
    And Saya mencari dan mengeklik project bernama "Erico's project team"
    Then Halaman detail project harus berhasil terbuka