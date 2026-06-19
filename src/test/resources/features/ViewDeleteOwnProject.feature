
Feature: Melihat Projek yang Telah di Upload
  Scenario:
    Given Saya sudah login ke aplikasi SIMPAD
    When Saya membuka menu Upload Project
    And Saya mengunggah file portofolio
    And Saya mengisi detail project dengan judul "Project Dummy Untuk Dihapus", nama tim "Tim QA", link "https://www.youtube.com/watch?v=kYmoT-wBX08", dan deskripsi "Project ini dibuat khusus untuk menguji fitur Delete."
    And Saya memilih user "Rainard", "Erico", dan "Nadia Putri"
    And Saya memberikan role "Front-end", "Back-end", dan "UI/UX" secara berurutan
    And Saya menekan tombol Post
    And Project harus berhasil dipublikasikan
    And Saya melakukan scroll di halaman profil saya
    When Saya menekan tombol Explore PAD untuk ke halaman project
    And Saya melakukan scroll di halaman detail project
    And Saya membuka menu Project
    And Saya melakukan scroll untuk melihat daftar project
    And Saya membuka menu profil dan masuk ke halaman profil saya
    And Saya melakukan scroll di halaman profil saya
    And Saya menekan tombol Edit Project
    And Saya menekan tombol Delete Project
    And Saya menyetujui pop up konfirmasi hapus project
    And Saya membuka menu Project
    And Saya melakukan scroll untuk melihat daftar project
    And Saya membuka menu profil dan masuk ke halaman profil saya
    And Saya melakukan scroll di halaman profil saya