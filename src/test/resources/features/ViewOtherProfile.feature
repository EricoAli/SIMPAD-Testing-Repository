Feature: Melihat Project Orang Lain Tanpa Login

  Scenario: Pengguna publik (belum login) melihat detail project dan profil pembuat
    Given Saya berada di halaman utama SIMPAD
    When Saya membuka menu Project
    And Saya mencari dan mengeklik project bernama "Erico's project team"
    And Saya mengeklik foto profil anggota tim
    Then Halaman profil anggota tim harus berhasil terbuka