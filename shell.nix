with import <nixpkgs> {};
mkShell.override { stdenv = llvmPackages_12.stdenv; } {
    buildInputs = [
        openjdk
        shellcheck
    ];
    shellHook = ''
        . .shellhook
    '';
}
