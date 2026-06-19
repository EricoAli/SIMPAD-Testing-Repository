Feature: Fitur Upload Project Portfolio SIMPAD

  Background:
    Given Saya sudah login ke aplikasi SIMPAD

  Scenario: Mengunggah project baru (Happy Path)
    When Saya membuka menu Upload Project
    And Saya mengunggah file portofolio
    # Contoh pemakaian di file .feature
    And Saya mengisi detail project dengan judul "Sistem DAQ Mobil Listrik", nama tim "Tim Mobil Intinya", link "https://www.youtube.com/watch?v=kYmoT-wBX08", dan deskripsi "Sistem IoT sederhana pada mobil balap ntah apalah itu"
    And Saya memilih user "Rainard", "Erico", dan "Nadia Putri"
    And Saya memberikan role "Front-end", "Back-end", dan "UI/UX" secara berurutan


    Then Project harus berhasil dipublikasikan