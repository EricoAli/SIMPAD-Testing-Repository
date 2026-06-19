Feature: Melihat Profile Orang Lain

  Scenario: Pengguna publik (belum login) melihat detail project dan profil pembuat
    Given Saya sudah login ke aplikasi SIMPAD
    When Saya membuka menu Project
    And Saya mencari dan mengeklik project bernama "Erico's project team"
    And Saya mengeklik foto profil anggota tim
    Then Halaman profil anggota tim harus berhasil terbuka