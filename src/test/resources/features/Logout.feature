Feature: Logout dari Aplikasi SIMPAD

  Scenario: Pengguna melakukan login kemudian langsung logout
    # Menggunakan step login yang sudah Anda buat dan dijamin sukses
    Given Saya sudah login ke aplikasi SIMPAD

    # Memanggil step logout yang baru saja dibuat
    When Saya membuka profil dan menekan tombol Logout

    # Validasi
    Then Saya harus berhasil keluar dan kembali ke halaman utama