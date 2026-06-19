@Fokus
Feature: End-to-End Flow Aplikasi SIMPAD (Revisi)

  Scenario: Eksplorasi Publik, Login, Tambah Project, Validasi, lalu Logout
#    Mengecek project orang lain dalam mode publik dan membuka profile orang lain
    Given Saya berada di halaman utama SIMPAD
    When Saya membuka menu Project
    And Saya mencari dan mengeklik project bernama "Erico's project team"
    And Saya mengeklik foto profil anggota tim
    And Halaman profil anggota tim harus berhasil terbuka

#    Login
    And Saya sudah login ke aplikasi SIMPAD

#    Menambahkan project dan mengepostnya
    And Saya membuka menu Upload Project
    And Saya mengunggah file portofolio
    And Saya mengisi detail project dengan judul "Sistem DAQ Mobil Listrik", nama tim "Tim Mobil Intinya", link "https://www.youtube.com/watch?v=kYmoT-wBX08", dan deskripsi "Sistem IoT sederhana pada mobil balap ntah apalah itu"
    And Saya memilih user "Rainard", "Erico", dan "Nadia Putri"
    And Saya memberikan role "Front-end", "Back-end", dan "UI/UX" secara berurutan
    And Saya menekan tombol Post
    And Project harus berhasil dipublikasikan

#    Mengecek Project yang ditambahkan
    And Saya melakukan scroll di halaman profil saya
    When Saya menekan tombol Explore PAD untuk ke halaman project
    And Saya melakukan scroll di halaman detail project
    And Saya membuka menu Project
    And Saya melakukan scroll untuk melihat daftar project

##    Mengedit Project
#    And Saya membuka menu profil dan masuk ke halaman profil saya
#    And Saya melakukan scroll di halaman profil saya
#    And Saya menekan tombol Edit Project
#    And Saya menekan tombol Edit Project
#    And Saya mengubah detail project dengan judul "Sistem DAQ Motor Listrik", gambar baru, link "https://www.youtube.com/watch?v=kYmoT-wBX08", dan deskripsi "Update: Sistem IoT dan DAQ ini dikembangkan lebih lanjut bersama tim"
#    And Saya menyimpan perubahan project
#    When Saya menekan tombol Explore PAD untuk ke halaman project
#    And Saya melakukan scroll di halaman detail project
#    And Saya membuka menu Project
#    And Saya melakukan scroll untuk melihat daftar project

#    Menghapus project
    And Saya membuka menu profil dan masuk ke halaman profil saya
    And Saya melakukan scroll di halaman profil saya
    And Saya menekan tombol Edit Project
    And Saya menekan tombol Delete Project
    And Saya menyetujui pop up konfirmasi hapus project
    And Saya membuka menu Project
    And Saya melakukan scroll untuk melihat daftar project
    And Saya membuka menu profil dan masuk ke halaman profil saya
    And Saya melakukan scroll di halaman profil saya

#    Logout
    And Saya membuka profil dan menekan tombol Logout
    Then Saya harus berhasil keluar dan kembali ke halaman utama