with import <nixpkgs> {};
mkShell.override { stdenv = llvmPackages_19.stdenv; } {
    buildInputs = [
        openjdk
        shellcheck
    ];
    shellHook = ''
        . .shellhook
    '';
}
