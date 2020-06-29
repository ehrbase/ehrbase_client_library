#!/usr/bin/env bash

shopt -s extglob
case $last_commit in
    # Check if the two substr 'major' AND 'release'
    # are contained in $last_commit message.
    # NOTE: this is a workaround for the non existing AND operator in bash's CASE
    #       using double negation with OR as described in
    #       https://unix.stackexchange.com/questions/549165/and-operator-in-case-statement#549316
    ( !(!(*"major"*)|!(*"release"*)) )
        echo "deploy major release"
        # exit 0
        ;;
    ( !(!(*"minor"*)|!(*"release"*)) )
        echo "deploy minor release"
        # exit 0
        ;;
    ( !(!(*"patch"*)|!(*"release"*)) )
        echo "deploy patch release"
        # exit 0
        ;;
    *)
        echo "do nothing"
        # exit 0
        ;;
esac
