#!/bin/bash
echo "Testing wait command"
sleep 5 &
pid=$!
kill $pid
wait $pid

