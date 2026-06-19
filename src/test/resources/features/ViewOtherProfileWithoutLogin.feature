Feature: Melihat Profil Orang Lain Tanpa Login

  Scenario: Pengguna publik (belum login) melihat profil anggota tim melalui halaman project
    # 1. Membuka halaman utama tanpa melakukan login
    Given Saya berada di halaman utama SIMPAD

    # 2. Masuk ke menu Project utama
    When Saya membuka menu Project

    # 3. Mencari dan membuka project milik orang lain (misal: Erico)
    And Saya mencari dan mengeklik project bernama "Erico's project team"

    # 4. Melakukan scroll dan klik pada foto profil anggota tim
    And Saya mengeklik foto profil anggota tim

    # 5. Memvalidasi bahwa halaman profil orang tersebut berhasil terbuka
    Then Halaman profil anggota tim harus berhasil terbuka