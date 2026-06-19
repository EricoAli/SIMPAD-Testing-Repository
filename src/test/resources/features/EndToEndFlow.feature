@Fokus
Feature: End-to-End Flow Aplikasi SIMPAD (Revisi)

  Scenario: Eksplorasi Publik, Login, Tambah Project, Validasi, lalu Logout
    # 1. Mengecek project orang lain (Mode Publik/Belum Login)
    Given Saya berada di halaman utama SIMPAD
    When Saya membuka menu Project
    And Saya mencari dan mengeklik project bernama "Erico's project team"

    # 2. Melihat profile orang lain
    And Saya mengeklik foto profil anggota tim
    And Halaman profil anggota tim harus berhasil terbuka

    # 3. Login ke dalam sistem
    # Cucumber akan membaca step ini dan menjalankan proses login Anda
    And Saya sudah login ke aplikasi SIMPAD

    # 4. Menambahkan project dan mengepostnya
    And Saya membuka menu Upload Project
    And Saya mengunggah file portofolio
    # Pastikan Judul di sini diingat baik-baik untuk pencarian di step berikutnya
    And Saya mengisi detail project dengan judul "Sistem DAQ Mobil Listrik", nama tim "Tim Mobil Intinya", link "https://www.youtube.com/watch?v=kYmoT-wBX08", dan deskripsi "Sistem IoT sederhana pada mobil balap ntah apalah itu"
    And Saya memilih user "Rainard", "Erico", dan "Nadia Putri"
    And Saya memberikan role "Front-end", "Back-end", dan "UI/UX" secara berurutan
    And Saya menekan tombol Post
    And Project harus berhasil dipublikasikan
#    And Saya berada di halaman utama SIMPAD

    # 5. Kembali ke laman project dan cari project yang baru saja ditambahkan
    And Saya membuka menu Project
    # Judul ini harus SAMA PERSIS dengan yang baru diinput di atas
    And Saya mencari dan mengeklik project bernama "Erico's project team"

    # 6. Logout
    And Saya membuka profil dan menekan tombol Logout
    Then Saya harus berhasil keluar dan kembali ke halaman utama