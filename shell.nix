with import <nixpkgs> {};
mkShell.override { stdenv = llvmPackages_16.stdenv; } {
    buildInputs = [
        openjdk
        shellcheck
    ];
    shellHook = ''
        . .shellhook
    '';
}
