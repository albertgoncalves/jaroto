with import <nixpkgs> {};
mkShell.override { stdenv = llvmPackages_17.stdenv; } {
    buildInputs = [
        openjdk
        shellcheck
    ];
    shellHook = ''
        . .shellhook
    '';
}
